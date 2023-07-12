//package com.example.register_login_jwt.config;
//
//import com.example.register_login_jwt.exception.UnauthorizedExceptionHandler;
//import com.example.register_login_jwt.model.entity.UserApp;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.util.UUID;
//
//public class GetCurrentUserConfig {
//    public UUID getCurrentUser() {
//        Object getContext = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (getContext.equals("anonymousUser")) {
//            throw new UnauthorizedExceptionHandler("Unauthorized");
//        }
//
//        UserApp userApp = (UserApp) getContext;
//        return userApp.getId();
//    }
//}
