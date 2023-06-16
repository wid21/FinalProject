package com.example.finalproject.Confiuration;

//import com.example.finalproject.Service.MyUserDetailsService;
import com.example.finalproject.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfig {


    private final MyUserDetailsService myUserDetailsService;


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//        return daoAuthenticationProvider;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/myuser/register-company","/api/v1/myuser/register-customer",
                        "/api/v1/details/add-details",
                        "/api/v1/details/add-details-toCompany/{Company_id}/{details_id}",
                        "/api/v1/staff/add-staff",
                        "/api/v1/staff/add-staff-toCompany/{Company_id}/{staff_id}",
                        "/api/v1/details/add-details-toStaff/{Company_id}/{details_id}/{staff_id}",
                       "/api/v1/booking/add-booking",
                        "/api/v1/booking/update-booking/{bookingId}",
                        "/api/v1/booking/delete-booking/{bookingId}",
                        "/api/v1/booking/change-status/{status}/{bookingId}",
                        "/api/v1/company/delete-Company",
                          "/api/v1/customer/update-customer",
                        "/api/v1/company/change-status/{status}/{companyId}",
                "/api/v1/booking/get-customerOrders",
                        "/api/v1/company/get-company",
                        "/api/v1/staff/get-staff",
                        "/api/v1/details/get-details",
                        "/api/v1/customer/get-customer",
                        "/api/v1/details/delete-details/{detailsId}",
                        "/api/v1/staff/delete-staff/{staffId}",
                        "/api/v1/staff/update-staff/{staffId}",
                        "/api/v1/details/update-details/{detailsId}",
                        "/api/v1/details/addto-company/{company_id}/{service_id}",
                        "/api/v1/company/update-company",
                "/api/v1/booking/getmc/{m_id}/{c_id}",
                        "/api/v1/customer/add-points",
                        "/api/v1/rate/add-rate").permitAll()

                //admin access
                .requestMatchers("/api/v1/auth/admin").hasAuthority("ADMIN")
                //user access
                .requestMatchers("/api/v1/myuser/login","/api/v1/myuser/logout").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}


