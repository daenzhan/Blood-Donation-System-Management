package org.example.bloodrequestservice;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BloodRequestService {

    private final BloodRequestRepository repository;

    public BloodRequestService(BloodRequestRepository repository) {
        this.repository = repository;
    }

    public List<BloodRequest> get_all_requests() {
        return repository.findAll();
    }

    public Optional<BloodRequest> get_request_by_id(Long id) {
        return repository.findById(id);
    }

    public BloodRequest save_request(BloodRequest request) {
        return repository.save(request);
    }

    public void delete_request(Long id) {
        repository.deleteById(id);
    }

    public List<BloodRequest> get_requests_by_medcenter(Long medcenter_id) {
        return repository.findByMedcenterId(medcenter_id);
    }
}