package com.example.register_login_jwt.service;

import com.example.register_login_jwt.model.dto.OrganizationDetailDTO;
import com.example.register_login_jwt.model.request.OrganizationDetailRequest;

public interface OrganizationDetailService {
    OrganizationDetailDTO addNewOrganizationDetail(OrganizationDetailRequest organizationDetailRequest);

}
