package com.pluriverse.learnSpring.controller;

import com.pluriverse.learnSpring.model.User;
import com.pluriverse.learnSpring.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Operation(summary = "Obtain all users", description = "Return a list with all users")
    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Create a user", description = "Create a new user in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success."),
            @ApiResponse(responseCode = "400", description = "Invalid data.",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("users/create")
    public void addUser(
            @Valid
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Required data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "User example",
                                    value = "{ \"name\": \"Peter\", \"email\": \"lorienlored@gmail.com\" }"
                            )
                    )
            )
            User user) {
        userService.addUser(user);
    }

    @Operation(summary = "Delete a user", description = "Delete a user by given id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success."),
            @ApiResponse(responseCode = "404", description = "Invalid data.")
    })
    @DeleteMapping("users/{id}")
    public void deleteUser(
            @Parameter(description = "Id of user that should be deleted", required = true)
            @PathVariable long id) {
        userService.deleteUser(id);
    }

    @Operation(summary = "Obtain details about user", description = "Obtain details about user given by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success."),
            @ApiResponse(responseCode = "404", description = "Invalid.")
    })
    @GetMapping("users/{id}")
    public EntityModel<User> getUser(
            @Parameter(description = "User id", required = true)
            @PathVariable long id) {
        EntityModel<User> userEntityModel = EntityModel.of(userService.getUser(id));
        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        userEntityModel.add(webMvcLinkBuilder.withRel("all-users"));
        return userEntityModel;
    }

    @Operation(summary = "Modify a user", description = "Modify given user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success."),
            @ApiResponse(responseCode = "400", description = "Invalid."),
            @ApiResponse(responseCode = "404", description = "Not found.")
    })
    @PutMapping("users/{id}")
    public void modifyUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Actual user data.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplu Modificare Utilizator",
                                    value = "{ \"name\": \"Peter\", \"email\": \"lorienlored@gmail.com\" }"
                            )
                    )
            )
            @RequestBody User modifiedUser,
            @Parameter(description = "ID-ul utilizatorului care trebuie modificat.", required = true)
            @PathVariable long id) {
        userService.modifyUser(modifiedUser, id);
    }

    @GetMapping("i18n/hello")
    public String i18nHello() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("hello.message", null, "Hello all!", locale);
    }
}
