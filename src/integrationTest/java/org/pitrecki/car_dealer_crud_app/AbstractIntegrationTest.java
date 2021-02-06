package org.pitrecki.car_dealer_crud_app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pitrecki.car_dealer_crud_app.helpers.ParamEntries;
import org.pitrecki.car_dealer_crud_app.helpers.RequestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:integration-tests.properties")
@DirtiesContext(classMode = BEFORE_CLASS)
public abstract class AbstractIntegrationTest {

    @Autowired private MockMvc mvc;

    @BeforeAll
    public static void setup(@Autowired DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("sql/test_population.sql"));
        }
    }

    protected ResultActions doPost(String url, String content) throws Exception {
        return RequestHelper.doPost(mvc, url, content);
    }

    protected ResultActions doGet(String url, ParamEntries params) throws Exception {
        return RequestHelper.doGet(mvc, url, params);
    }

    protected ResultActions doPut(String url, ParamEntries params) throws Exception {
        return RequestHelper.doPut(mvc, url, params);
    }
}
