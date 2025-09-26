package org.example.medcenterserver;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedCenterService {

    private final MedCenterRepository medCenterRepository;
    private final UserFeignClient user_client;

    public MedCenterService(MedCenterRepository medCenterRepository) {
        this.medCenterRepository = medCenterRepository;
    }

    public MedCenter create(MedCenter m_c) {
        return medCenterRepository.save(m_c);
    }

    public List<MedCenter> get_all() {
        return medCenterRepository.findAll();
    }

    public MedCenter get_by_id(Long med_center_id) {
        return medCenterRepository.findById(med_center_id);
    }

    public MedCenter update(Long med_center_id, MedCenter new_m_c) {
        MedCenter m_c = get_med_center_by_id(id);
        m_c.setName(new_m_c.getName());
        m_c.setLocation(new_m_c.getAddress());
        m_c.setPhone(new_m_c.getPhone());
        m_c.setSpecialization(new_m_c.getSpecialization());
        m_c.setUser_id(new_m_c.getUserId());
        return medCenterRepository.save(m_c);
    }

    public void delete(Long id) {
        medCenterRepository.deleteById(id);
    }
}