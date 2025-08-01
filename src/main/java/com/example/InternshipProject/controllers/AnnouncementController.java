package com.example.InternshipProject.controllers;

import com.example.InternshipProject.services.abstracts.AnnouncementService;
import com.example.InternshipProject.services.dtos.requests.CreateAnnouncementRequest;
import com.example.InternshipProject.services.dtos.responses.AnnouncementResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping
    public ResponseEntity<Void> createAnnouncement(@RequestBody CreateAnnouncementRequest request) {
        announcementService.createAnnouncement(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/recent")
    public ResponseEntity<List<AnnouncementResponse>> getRecentAnnouncements(@RequestParam("email") String email) {
        return ResponseEntity.ok(announcementService.getRecentAnnouncements(email));
    }

}
