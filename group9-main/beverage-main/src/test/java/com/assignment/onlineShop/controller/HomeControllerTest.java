//package com.assignment.onlineShop.controller;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
//import static org.mockito.ArgumentMatchers.any;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.assignment.onlineShop.repository.entity.WebUser;
//import com.assignment.onlineShop.service.BottleService;
//import com.assignment.onlineShop.service.CartService;
//import com.assignment.onlineShop.service.CrateService;
//import com.assignment.onlineShop.service.model.CrateDto;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class HomeControllerTest {
//
//    @Mock
//    private CartService cartService;
//    @Mock
//    private CrateService crateService;
//    @Mock
//    private BottleService bottleService;
//    @Autowired
//    private MockMvc mvc;
//
//    private List<CrateDto> crates;
//    private WebUser adminUser;
//    private WebUser user;
//
//    @BeforeEach
//    public void initCommonUsedData() {
//        this.crates = new ArrayList<>();
//        this.crates.add(new CrateDto(1L, "Test Crate", "beer_crate.jpg", 200, 75.0, 10, List.of()));
//        this.adminUser = new WebUser("testAdmin", "super", "Super User", "+49 170", "ROLE_ADMIN");
//        this.user = new WebUser("testUser", "normal", "User", "+49 171", "ROLE_USER");
//    }
//
//    private void findAllCrates() {
//        when(this.crateService.getAll()).thenReturn(crates);
//    }
//
//    private MockHttpServletRequestBuilder createGetRequestBuilder() {
//        return MockMvcRequestBuilders.get("/");
//    }
//
//    private MockHttpServletRequestBuilder createPostRequestBuilder(CrateDto crateDto) {
//        return MockMvcRequestBuilders.post("/")
//                .param("name", crateDto.getName())
//                .param("price", "" + crateDto.getPrice());
//    }
//
//    @Test
//    public void getRequestCrates_anonymousUser_redirectToLogin() throws Exception {
//        this.mvc.perform(this.createGetRequestBuilder().with(anonymous()))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(redirectedUrlPattern("**/login"));
//    }
//
//    @Test
//    public void getRequestCrates_authenticatedUser_gettingHTMLModel() throws Exception {
//        this.findAllCrates();
//        this.mvc.perform(this.createGetRequestBuilder().with(user(this.user)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.model().size(4))
//                .andExpect(MockMvcResultMatchers.model().attribute("crates", this.crates));
//    }
//
//    @Test
//    public void getRequestCrates_authenticatedAdmin_gettingHTMLModel() throws Exception {
//        this.findAllCrates();
//        this.mvc.perform(
//                        this.createGetRequestBuilder().with(user(this.adminUser)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.model().size(4))
//                .andExpect(MockMvcResultMatchers.model().attribute("crates", this.crates));
//    }
//
//    @Test
//    public void postRequestCrates_authenticatedUser_forbidden() throws Exception {
//        this.findAllCrates();
//        this.mvc.perform(this.createPostRequestBuilder(this.crates.get(0)).with(user(this.user)))
//                .andExpect(MockMvcResultMatchers.status().isForbidden());
//    }
//
//    @Test
//    public void postRequestCrates_authenticatedAdminCreateCrate_updatedCrateList() throws Exception {
//
//        this.findAllCrates();
//        CrateDto c = new CrateDto(1L, "Test Crate", "beer_crate.jpg", 200, 75.0, 10, List.of());
//
//        List<CrateDto> spyList = Mockito.spy(this.crates);
//        when(this.crateService.saveCrate(any(CrateDto.class))).thenAnswer(invocation -> {
//            CrateDto requested = invocation.getArgument(0, CrateDto.class);
//            spyList.add(requested);
//            return requested;
//        });
//
//        this.mvc.perform(this.createPostRequestBuilder(c).with(csrf()).with(user(this.adminUser)))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
//
//        verify(this.crateService).saveCrate(c);
//        verify(spyList, times(1)).add(any(CrateDto.class));
//
//        assertEquals(2, spyList.size());
//    }
//
//    @Test
//    public void postRequestCrates_authenticatedAdminCreateCrate_validationError() throws Exception {
//        this.findAllCrates();
//        CrateDto c = new CrateDto(1L, "Test Crate", "beer_crate.jpg", 200, 75.0, 10, List.of());
//        this.mvc.perform(this.createPostRequestBuilder(c).with(csrf()).with(user(this.adminUser)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.model().hasErrors());
//    }
//}