package com.picpayclone.resource.swagger;

import com.picpayclone.dto.ErrorDTO;
import com.picpayclone.dto.UserDTO;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(value = "/users", description = "Users related operations")
public interface IUserResource {

    // METHOD 1 - BALANCE CONSULT
    @ApiOperation(value = "User balance consult by login", nickname = "balanceConsult", notes = "",
    response = UserDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "users" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful balance consult", response = UserDTO.class,
                    responseContainer = "object"),
            @ApiResponse(code = 400, message = "Inconsistent informed data for request",
                    response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "User not allowed to access this resource"),
            @ApiResponse(code = 404, message = "User not found") })
    @GetMapping("/{login}/balance")
    public ResponseEntity<UserDTO> userBalanceConsult(@PageableDefault(page = 0, size = 20)
                                                                  Pageable pagination,
                                                      @PathVariable String login);

    // METHOD 2 - USER CONTACTS CONSULT
    @ApiOperation(value = "User contacts consult by login", nickname = "contactList", notes = "",
            response = UserDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "basicAuth") }, tags = { "users" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful found contacts", response = UserDTO.class,
                    responseContainer = "object"),
            @ApiResponse(code = 400, message = "Inconsistent informed data for request",
                    response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "User not allowed to access this resource"),
            @ApiResponse(code = 404, message = "User not found") })
    @GetMapping("/contacts")
    public ResponseEntity<List<UserDTO>> listContacts(@RequestParam String login);

    // METHOD 3 - USER CONSULT
    @ApiOperation(value = "User consult by login", nickname = "usersConsult", notes = "",
            response = UserDTO.class, responseContainer = "object", authorizations = {
            @Authorization(value = "basicAuth") }, tags = { "users" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful found user", response = UserDTO.class,
                    responseContainer = "object"),
            @ApiResponse(code = 400, message = "Inconsistent informed data for request",
                    response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "User not allowed to access this resource"),
            @ApiResponse(code = 404, message = "User not found") })
    @GetMapping("/{login}")
    public ResponseEntity<UserDTO> userConsult(@PathVariable String login);

}
