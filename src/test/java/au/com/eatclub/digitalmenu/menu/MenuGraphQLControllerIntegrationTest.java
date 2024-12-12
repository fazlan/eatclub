package au.com.eatclub.digitalmenu.menu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Import({MenuStubRepository.class})
@GraphQlTest(controllers = MenuGraphQLController.class)
class MenuGraphQLControllerIntegrationTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void givenKebabJointBranch_whenQueriedForRecentMenu_thenReturnResponseShouldContainAllMenuSectionsAndItems() throws IOException {
        String documentName = "query_recent_menu";

        graphQlTester.documentName(documentName)
                .variable("branchId", 1)
                .execute()
                .path("$")
                .matchesJson(expected("query_kebab_joint_recent_menu"));
    }

    @Test
    void givenPizzaBoysBranch_whenQueriedForRecentMenu_thenReturnResponseShouldContainAllMenuSectionsAndItems() throws IOException {
        String documentName = "query_recent_menu";

        graphQlTester.documentName(documentName)
                .variable("branchId", 3)
                .execute()
                .path("$")
                .matchesJson(expected("query_pizza_boys_recent_menu"));
    }

    public static String expected(String fileName) throws IOException {
        Path path = Paths.get("src/test/resources/graphql-test/" + fileName + "_expected_response.json");
        return new String(Files.readAllBytes(path));
    }
}
