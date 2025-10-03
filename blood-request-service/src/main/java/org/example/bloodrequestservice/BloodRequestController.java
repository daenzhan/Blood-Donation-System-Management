package org.example.bloodrequestservice;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/requests")
public class BloodRequestController {

    private final BloodRequestService service;

    public BloodRequestController(BloodRequestService service) {
        this.service = service;
    }

    @GetMapping
    public String list_requests(Model model) {
        model.addAttribute("requests", service.get_all_requests());
        return "list";
    }

    @GetMapping("/new")
    public String create_request(Model model) {
        model.addAttribute("bloodRequest", new BloodRequest());
        return "form";
    }

    @PostMapping("/save")
    public String save_request(@ModelAttribute BloodRequest blood_request) {
        service.save_request(blood_request);
        return "redirect:/requests";
    }

    @GetMapping("/{id}/edit")
    public String edit_request(@PathVariable Long id, Model model) {
        var opt = service.get_request_by_id(id);
        if (opt.isEmpty()) {
            return "redirect:/requests";
        }
        model.addAttribute("blood_request", opt.get());
        return "form";
    }

    @PostMapping("/{id}/delete")
    public String delete_request(@PathVariable Long id) {
        service.delete_request(id);
        return "redirect:/requests";
    }

    @GetMapping("/{id}")
    public String view_details(@PathVariable Long id, Model model) {
        var opt = service.get_request_by_id(id);
        if (opt.isEmpty()) {
            return "redirect:/requests";
        }
        model.addAttribute("request", opt.get());
        return "details";
    }

    @GetMapping("/medcenter/{medcenter_id}")
    public String list_by_medcenter(@PathVariable Long medcenter_id, Model model) {
        model.addAttribute("requests", service.get_requests_by_medcenter(medcenter_id));
        model.addAttribute("medcenter_id", medcenter_id);
        return "list-by-medcenter";
    }

}