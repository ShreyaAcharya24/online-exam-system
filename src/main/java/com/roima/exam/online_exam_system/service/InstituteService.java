package com.roima.exam.online_exam_system.service;

import com.roima.exam.online_exam_system.dto.InstituteDTO;
import com.roima.exam.online_exam_system.model.Institute;
import com.roima.exam.online_exam_system.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstituteService {

    private InstituteRepository instituteRepository;

    @Autowired
    public InstituteService(InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    //    Create New Institute
    public InstituteDTO saveInstitute(InstituteDTO instituteDTO) {
        var institute = toInstitute(instituteDTO);
        var createdInstitute = instituteRepository.save(institute);
        return toInstituteDTO(createdInstitute);
    }

    //    Get All Institutes
    public List<InstituteDTO> getAllInstitutes() {
        return instituteRepository.findAll().stream().map(this::toInstituteDTO).collect(Collectors.toList());
    }

    //    Delete Institute by Id
    public boolean deleteInstitute(int id) {
        if (instituteRepository.existsById(id)) {
            instituteRepository.deleteById(id);
            return true;
        }
        return false;
    }

//    Find Institute by Id

    public Institute findById(int id) {
        return instituteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Institute Not found"));
    }

//    Update Institute


    //   ******* Coversion Methods
    public Institute toInstitute(InstituteDTO instituteDTO) {

        var institute = new Institute();
        institute.setInstituteId(instituteDTO.instituteID());
        institute.setInstituteName(instituteDTO.institutename());
        institute.setContact(instituteDTO.contact());
        institute.setAddress(instituteDTO.address());

        return institute;
    }

    public InstituteDTO toInstituteDTO(Institute institute) {

        return new InstituteDTO(institute.getInstituteId(), institute.getInstituteName(), institute.getContact(), institute.getAddress());
    }

}
