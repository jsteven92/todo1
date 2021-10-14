package todo1.sysiems.store.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import todo1.sysiems.store.helper.FormatMessage;
import todo1.sysiems.store.security.JwtTokenUtil;
import todo1.sysiems.store.security.JwtUser;
import todo1.sysiems.store.security.model.User;
import todo1.sysiems.store.security.service.UserService;

@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    private FormatMessage message = new FormatMessage();

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }

    @PostMapping
    @RequestMapping(value = "/user")
    public ResponseEntity<User> createProduct(@Valid @RequestBody User user, BindingResult result) {

        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message.formatMessage(result));
        }
        
        System.out.println(user);
        
        User userCreate = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreate);
    }

}
