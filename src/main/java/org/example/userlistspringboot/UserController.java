package org.example.userlistspringboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Validated
public class UserController {
    private List<User> userList = new ArrayList<>();

    public UserController() {
        // Initialize the user list
        userList.add(new User(1, "John", "Bob", LocalDate.of(1980, 5, 15), new Address("Street1", "New York"), List.of(new Profession("Engineer"))));
        userList.add(new User(2, "Alice", "Smith", LocalDate.of(1990, 8, 25), new Address("Street2", "Los Angeles"), List.of(new Profession("Teacher"))));
        userList.add(new User(3, "Bob", "Johnson", LocalDate.of(1985, 3, 10), new Address("Street3", "Yerevan"), List.of(new Profession("Doctor"))));
        userList.add(new User(4, "Bob", "Johnson", LocalDate.of(1975, 3, 10), new Address("Street4", "Vanadzor"), List.of(new Profession("Doctor"))));
        userList.add(new User(5, "Max", "Rao", LocalDate.of(1965, 3, 10), new Address("Bob", "Gyumri"), List.of(new Profession("HR"))));
        userList.add(new User(6, "Bob", "Melkonyan", LocalDate.of(1975, 3, 10), new Address("Street5", "Vanadzor"), List.of(new Profession("Engineer"))));
        userList.add(new User(7, "Vika", "Simonyan", LocalDate.of(1975, 3, 10), new Address("Street6", "Berlin"), List.of(new Profession("Doctor"))));
        userList.add(new User(8, "Max", "Rao", LocalDate.of(1975, 3, 10), new Address("Street6", "Berlin"), List.of(new Profession("HR"))));
        userList.add(new User(9, "Bob", "Melkonyan", LocalDate.of(1975, 3, 10), new Address("Street7", "Abu-Dhabi"), List.of(new Profession("Engineer"))));
        userList.add(new User(10, "Vika", "Simonyan", LocalDate.of(1995, 3, 10), new Address("Street8", "Los"), List.of(new Profession("Doctor"))));
        userList.add(new User(11, "Bob", "Melkonyan", LocalDate.of(1985, 3, 10), new Address("Street9", "Chicago"), List.of(new Profession("Engineer"))));
        userList.add(new User(12, "Vika", "Simonyan", LocalDate.of(1975, 3, 10), new Address("Street10", "Chicago"), List.of(new Profession("Doctor"))));
        userList.add(new User(13, "Anri", "Rao", LocalDate.of(1999, 3, 10), new Address("Street11", "Mexico"), List.of(new Profession("HR"))));
        userList.add(new User(14, "Bob", "Melkonyan", LocalDate.of(1998, 3, 10), new Address("Street12", "Italy"), List.of(new Profession("Engineer"))));
        userList.add(new User(15, "Vika", "Simonyan", LocalDate.of(1975, 3, 10), new Address("Street13", "Vanadzor"), List.of(new Profession("Doctor"))));
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

@PostMapping("/search")
public String search(@RequestParam("input") String input, Model model) {
    if (input.trim().isEmpty()) {
        model.addAttribute("error", "Input cannot be empty.Or write cancel in input ");

        return "index";
    }

    if (input.equalsIgnoreCase("cancel")) {
        model.addAttribute("message", "Operation cancelled by user.");
        return "cancel";  // Redirect to a cancel page or handle it appropriately
    }


    if (!input.matches("[a-zA-Z0-9 ]+")) {
        model.addAttribute("error", "Input contains invalid characters.");
        return "index";
    }

    List<User> result = findUsersByInput(input);
    model.addAttribute("users", result);
    return "result";
}

    private List<User> findUsersByInput(String input) {
        String sanitizedInput = input.toLowerCase().trim();
        return userList.stream()
                .filter(user -> user.getFirstName().toLowerCase().contains(sanitizedInput) ||
                        user.getLastName().toLowerCase().contains(sanitizedInput) ||
                        user.getAddress().getStreet().toLowerCase().contains(sanitizedInput))
                .collect(Collectors.toList());
    }
}
