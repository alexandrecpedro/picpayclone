package com.picpayclone.resource;

import com.picpayclone.dto.UserDTO;
import com.picpayclone.resource.swagger.IUserResource;
import com.picpayclone.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource extends ResourceBase<UserDTO> implements IUserResource {

    @Autowired
    private IUserService userService;

    // Recover user login
    @GetMapping("/{login}")
    public ResponseEntity<UserDTO> userConsult(@PathVariable String login) {
        UserDTO user = userService.consult(login);
        return responseItemSuccess(user);
    }

    // Recover contact list from a user
    @GetMapping("/contacts")
    public ResponseEntity<List<UserDTO>> listContacts (@RequestParam String login) {
        List<UserDTO> users = userService.list(login);
        return responseItemsList(users);
    }

    // Recover user balance
    @GetMapping("/{login}/balance")
    public ResponseEntity<UserDTO> userBalanceConsult(@PageableDefault(page = 0, size = 20) Pageable pagination,
                                                  @PathVariable String login) {
        UserDTO userDTO = userService.consult(login);
        return responseItemSuccess(userDTO);
    }
}
