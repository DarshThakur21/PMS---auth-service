package com.pms.auth_service.controller;

import com.pms.auth_service.dtos.LoginRequestDTO;
import com.pms.auth_service.dtos.LoginResponseDTO;
import com.pms.auth_service.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    @Autowired
    private AuthService authService;

    @Operation(summary = "Generate Token on the user login")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequestDTO){

        Optional<String> tokenOptional=authService.authenticate(loginRequestDTO);
        if(tokenOptional.isEmpty()){
            return new ResponseEntity<>("Invalid creds", HttpStatus.BAD_REQUEST);
        }
        String token=tokenOptional.get();
        return  ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @Operation(summary = "Validate Token")
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader ("Authorization") String authHeader){
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return authService.validateToken(authHeader.substring(7)) ?
                ResponseEntity.ok().build():
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
