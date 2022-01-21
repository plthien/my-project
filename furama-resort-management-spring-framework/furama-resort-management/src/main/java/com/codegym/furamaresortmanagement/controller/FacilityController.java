package com.codegym.furamaresortmanagement.controller;

import com.codegym.furamaresortmanagement.model.employee.Employee;
import com.codegym.furamaresortmanagement.model.facility.Facility;
import com.codegym.furamaresortmanagement.model.facility.RentingType;
import com.codegym.furamaresortmanagement.model.facility.ServiceType;
import com.codegym.furamaresortmanagement.service.facility.IFacilityService;
import com.codegym.furamaresortmanagement.service.facility.IRentingTypeService;
import com.codegym.furamaresortmanagement.service.facility.IServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/facilities")
public class FacilityController {
    @Autowired
    private IFacilityService iFacilityService;

    @Autowired
    private IRentingTypeService iRentingTypeService;

    @Autowired
    private IServiceTypeService iServiceTypeService;

    @ModelAttribute("rentingType")
    public List<RentingType> rentingTypes() {
        return iRentingTypeService.findAll();
    }

    @ModelAttribute("serviceType")
    public List<ServiceType> serviceTypes() {
        return iServiceTypeService.findAll();
    }

    @GetMapping()
    public String viewPageFacility(@RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "sortField", defaultValue = "name") String sortField,
                                   @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection,
                                   Optional<String> keyword,
                                   Optional<Integer> serviceTypeId,
                                   Model model
    ) {
        int size = 5;
        Page<Facility> facilityPage;
        if (!keyword.isPresent() || keyword.get().equals("")) {
            if (serviceTypeId.isPresent() && serviceTypeId.get() != 0) {
                facilityPage = iFacilityService.findFacilityByServiceType(page, size, sortField, sortDirection, serviceTypeId.get());
                model.addAttribute("serviceTypeId", serviceTypeId.get());
            } else {
                facilityPage = iFacilityService.findAllFacility(page, size, sortField, sortDirection);
            }
        } else {
            facilityPage = iFacilityService.findFacilityByKeyword(page, size, sortField, sortDirection, keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("facilities", facilityPage);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");
        return "facility/list";
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        return new ModelAndView("facility/create", "facility", new Facility());
    }

    @PostMapping("/create")
    public String saveFacility(@Valid @ModelAttribute("facility") Facility facility,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("facility", facility);
            System.out.println(bindingResult.toString());
            return "/facility/create";
        }
        facility.setStatus(1);
        iFacilityService.saveFacility(facility);
        model.addAttribute("facility", new Facility());
        model.addAttribute("message", "Add new Facility successfully!");
        return "/facility/create";
    }

    @GetMapping("/delete{id}")
    public String deleteFacility(@PathVariable("id") String id,
                                 RedirectAttributes redirectAttributes){
        iFacilityService.deleteFacility(id);
        redirectAttributes.addFlashAttribute("message","Delete employee successfully!");
        return "redirect:/facilities";
    }

    @GetMapping("/edit{id}")
    public ModelAndView showEditForm(@PathVariable("id") String id) {
        Facility facility = iFacilityService.findById(id);

        return new ModelAndView("facility/edit", "facility", facility);

    }

    @PostMapping("/edit")
    public String updateEmployee(@Valid @ModelAttribute("facility") Facility facility,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("facility", facility);
            return "/facility/edit";
        }

        iFacilityService.saveFacility(facility);
        model.addAttribute("facility", facility);
        model.addAttribute("message", "Edit facility successfully!");
        return "/facility/edit";

    }
}
