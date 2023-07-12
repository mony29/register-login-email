package com.example.register_login_jwt.service.serviceImp;

import com.example.register_login_jwt.exception.FieldBlankExceptionHandler;
import com.example.register_login_jwt.exception.FieldEmptyExceptionHandler;
import com.example.register_login_jwt.exception.NotFoundExceptionHandler;
import com.example.register_login_jwt.exception.UnauthorizedExceptionHandler;
import com.example.register_login_jwt.model.dto.OrganizationDetailDTO;
import com.example.register_login_jwt.model.entity.OrganizationDetail;
import com.example.register_login_jwt.model.entity.UserApp;
import com.example.register_login_jwt.model.mapper.OrganizationDetailMapper;
import com.example.register_login_jwt.model.request.OrganizationDetailRequest;
import com.example.register_login_jwt.repository.OrganizationDetailRepository;
import com.example.register_login_jwt.repository.OrganizationRepository;
import com.example.register_login_jwt.repository.UserAppRepository;
import com.example.register_login_jwt.service.OrganizationDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizationDetailServiceImp implements OrganizationDetailService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationDetailRepository organizationDetailRepository;
    private final UserAppRepository userAppRepository;
    private final OrganizationDetailMapper organizationDetailMapper;
    private UUID getCurrentUser() {

        Object getContext = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (getContext.equals("anonymousUser")) {
            throw new UnauthorizedExceptionHandler("Unauthorized User");
        }

        UserApp user = (UserApp) getContext;
        return user.getId();
    }
    @Override
    public OrganizationDetailDTO addNewOrganizationDetail(OrganizationDetailRequest organizationDetailRequest) {

        if (userAppRepository.getUserById(organizationDetailRequest.getUserId()) == null) {
            throw new NotFoundExceptionHandler("User not found");
        } else if (organizationDetailRequest.getOrganizationName().isEmpty()) {
            throw new FieldEmptyExceptionHandler("Organization name field is empty");
        } else if (organizationDetailRequest.getOrganizationName().isBlank()) {
            throw new FieldBlankExceptionHandler("Organization name field is blank");
        }

        Random random;
        String code;

        while (true) {
            random = new Random();
            code = String.format("%06d", random.nextInt(999999));

            if (organizationRepository.getOrganizationByCode(code) == null) {
                break;
            }
        }

        UUID id = organizationDetailRepository.addOrganization(organizationDetailRequest, code);
        OrganizationDetail organizationDetail = organizationDetailRepository.userAddNewOrganizationDetail(organizationDetailRequest.getUserId(), id);

//        System.out.println("--- Organization Detail --- " + organizationDetail);
        return organizationDetailMapper.INSTANCE.toOrganizationDetailDto(organizationDetail);
    }

}
