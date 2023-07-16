package com.example.register_login_jwt.service.serviceImp;

import com.example.register_login_jwt.exception.FieldBlankExceptionHandler;
import com.example.register_login_jwt.exception.FieldEmptyExceptionHandler;
import com.example.register_login_jwt.exception.NotFoundExceptionHandler;
import com.example.register_login_jwt.exception.UnauthorizedExceptionHandler;
import com.example.register_login_jwt.model.dto.OrganizationDTO;
import com.example.register_login_jwt.model.entity.Organization;
import com.example.register_login_jwt.model.entity.UserApp;
import com.example.register_login_jwt.model.mapper.OrganizationMapper;
import com.example.register_login_jwt.model.request.OrganizationRequest;
import com.example.register_login_jwt.repository.OrganizationRepository;
import com.example.register_login_jwt.repository.UserAppRepository;
import com.example.register_login_jwt.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImp implements OrganizationService {
    private final UserAppRepository userAppRepository;
    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    private UUID getCurrentUser() {
        Object getContext = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (getContext.equals("anonymousUser")) {
            throw new UnauthorizedExceptionHandler("Unauthorized");
        }
        UserApp userApp = (UserApp) getContext;
        return userApp.getId();
    }

    @Override
    public UUID createOrganization(OrganizationRequest organizationRequest) {

        UserApp user = userAppRepository.getUserById(getCurrentUser());

        if (user == null) {
            throw new NotFoundExceptionHandler("User not found");
        } else if (organizationRequest.getName().isEmpty()) {
            throw new FieldEmptyExceptionHandler("Organization name field is empty");
        } else if (organizationRequest.getName().isBlank()) {
            throw new FieldBlankExceptionHandler("Organization name field is blank");
        }

        String code = "";
        while (true) {
            Random random = new Random();
            code = String.format("%06d", random.nextInt(999999));

            if (organizationRepository.getOrganizationByCode(code) == null) {
                break;
            }
        }

        UUID organizationId = organizationRepository.createOrganization(organizationRequest, code, getCurrentUser());

        organizationRepository.addOrganizationDetail(getCurrentUser(), organizationId, getCurrentUser());

        return organizationId;
    }

    @Override
    public OrganizationDTO getOrganizationById(UUID organizationId) {

        if(getCurrentUser() == null){
            throw new UnauthorizedExceptionHandler("Unauthorized");
        }

        Organization organization = organizationRepository.getOrganizationById(organizationId);

        if(organization == null){
            throw new NotFoundExceptionHandler("Organization not found");
        }

        return organizationMapper.INSTANCE.toOrganizationDto(organization);
    }

    @Override
    public List<OrganizationDTO> getAllOrganization() {

        if(getCurrentUser() == null){
            throw new UnauthorizedExceptionHandler("Unauthorized");
        }

        List<Organization> organizations = organizationRepository.getAllOrganization();

        if(organizations == null){
            throw new NotFoundExceptionHandler("Organization not found");
        }

        return organizationMapper.INSTANCE.toOrganizationDto(organizations);
    }
}
