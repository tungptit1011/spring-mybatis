package com.ptit.mybatis.controller.auth;

import com.ptit.mybatis.config.JwtUtil;
import com.ptit.mybatis.dto.auth.AuthenticationRequest;
import com.ptit.mybatis.dto.auth.AuthenticationResponse;
import com.ptit.mybatis.service.CustomUserDetailsService;
import com.ptit.mybatis.utils.BaseResponse;
import com.ptit.mybatis.utils.ConstantUrl;
import com.ptit.mybatis.utils.Meta;
import io.jsonwebtoken.impl.DefaultClaims;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(ConstantUrl.V1_API)
@Slf4j
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/authenticate")
    @Operation(summary = "get token")
    public BaseResponse<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtUtil.generateToken(userdetails);
        log.info("token: {}", token);
        return new BaseResponse<>(new Meta("200", "Success"), new AuthenticationResponse(token));
    }

    @GetMapping(value = "/refreshtoken")
    @Operation(summary = "refresh token")
    public BaseResponse<AuthenticationResponse> refreshtoken(HttpServletRequest request) throws Exception {
        // From the HttpRequest get the claims
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        log.info("token: {}", token);
        return new BaseResponse<>(new Meta("200", "Success"), new AuthenticationResponse(token));
    }

    private Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }
}
