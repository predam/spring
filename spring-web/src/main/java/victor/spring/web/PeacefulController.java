package victor.spring.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PeacefulController {
    private final SecureService secureService;

    @GetMapping("rest")
    public String rest() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        secureService.sendSMSSafe();
        return "Peace on you " + user.getUsername();
    }


    @GetMapping("cantina-smechera")
    @PreAuthorize("hasRole('ADMIN')")
    public String cantinaSmechera() {
        return "Totu la 30%";
    }

    @GetMapping("login-info")
    public String loginInfo() {
        return "Suna-l Pe Sefu";
    }
    @GetMapping("admin/corner")
    public String cornerOffice() {
        secureService.sendSMSSafe();
        return "Ficus";
    }
    @GetMapping("admin/masina/serviciu")
    public String masinaDeServiciu() {
        return "De Serviciu";
    }
}
