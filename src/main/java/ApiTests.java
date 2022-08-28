import adapters.DefectAdapter;
import adapters.ProjectAdapter;
import com.google.gson.Gson;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTests {

    private final static Gson gson = new Gson();
    private final String expectedJsonAllProjects = "{\"status\":true,\"result\":{\"total\":4,\"filtered\":4,\"count\":4,\"entities\":[{\"title\":\"Demo Project\",\"code\":\"DEMO\",\"counts\":{\"cases\":10,\"suites\":3,\"milestones\":2,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":0,\"open\":0}}},{\"title\":\"Group QA19\",\"code\":\"QA\",\"counts\":{\"cases\":10,\"suites\":1,\"milestones\":0,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":8,\"open\":8}}},{\"title\":\"ADFDSFSF\",\"code\":\"CODE4\",\"counts\":{\"cases\":0,\"suites\":0,\"milestones\":0,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":0,\"open\":0}}},{\"title\":\"CODE5\",\"code\":\"CODE5\",\"counts\":{\"cases\":0,\"suites\":0,\"milestones\":0,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":3,\"open\":3}}}]}}";
    private final String expectedJsonDeleteProject = "{\"status\":true}";
    private final String expectedJsonAllDefects = "{\"status\":true,\"result\":{\"total\":8,\"filtered\":8,\"count\":8,\"entities\":[{\"id\":1,\"title\":\"Total cost calculation\",\"actual_result\":\"Discount increases total cost of a book added to the Shopping cart.";
    private final String expectedJsonDeleteDefect = "{\"status\":true,\"result\":{\"id\":8}}";
    private final String deleteProjectCode = "CODE5";
    private final String getCode = "DEMO";
    private final String projectCode = "QA";
    private final int id = 8;
    private final int getId = 2;
    private final int updateId = 1;
    ProjectAdapter projectAdapter = new ProjectAdapter();
    DefectAdapter defectAdapter = new DefectAdapter();

    @Test
    public void getAllProjectsTest() {
        String responseBody = projectAdapter.getAllProjects(200);
        Assert.assertEquals(responseBody, expectedJsonAllProjects);
    }

    @Test
    public void createProjectTest() {
        String testCode = "CODE6";

        Project project = Project.builder()
                .title("ADFDSFSF")
                .code(testCode)
                .description("efsfsdfsdfsdf")
                .build();

        ProjectResponse expectedProjectResponseBody = ProjectResponse
                .builder()
                .result(ProjectResult
                        .builder()
                        .code(testCode)
                        .build())
                .build();

        String actualResponseBody = projectAdapter.createProject(200, gson.toJson(project));
        Assert.assertEquals(gson.fromJson(actualResponseBody, ProjectResponse.class),
                expectedProjectResponseBody);
    }

    @Test
    public void getProjectByCodeTest() {
        String actualResponseBody = projectAdapter.getProjectByCode(200, getCode);
        ProjectResponse expectedProjectResponseBody = ProjectResponse
                .builder()
                .result(ProjectResult
                        .builder()
                        .code(getCode)
                        .build())
                .build();

        Assert.assertEquals(gson.fromJson(actualResponseBody, ProjectResponse.class),
                expectedProjectResponseBody);
    }

    @Test
    public void deleteProject(){
        String responseBody = projectAdapter.deleteProjectByCode(200,deleteProjectCode);
        Assert.assertEquals(responseBody, expectedJsonDeleteProject);
    }
    @Test
    public void getAllDefectTest() {
        String responseBody = defectAdapter.getAllDefects(200, projectCode);
        Assert.assertEquals(responseBody, expectedJsonAllDefects);
    }
    @Test
    public void createDefectTest() {
        Defect defect = Defect.builder()
                .title("Authorization")
                .actualResult("Impossible to authorize")
                .severity(4)
                .build();

        DefectResponse expectedDefectResponseBody = DefectResponse
                .builder()
                .result(DefectResult
                        .builder()
                        .id(id)
                        .build())
                .build();

        String actualResponseBody = defectAdapter.createDefect(200, projectCode, gson.toJson(defect));
        Assert.assertEquals(gson.fromJson(actualResponseBody, DefectResponse.class),
                expectedDefectResponseBody);
    }
    @Test
    public void getSpecificDefectTest() {

        DefectResponse expectedDefectResponseBody = DefectResponse
                .builder()
                .result(DefectResult
                        .builder()
                        .id(getId)
                        .build())
                .build();

        String actualResponseBody = defectAdapter.getSpecificDefect(200, projectCode, getId);
        Assert.assertEquals(gson.fromJson(actualResponseBody, DefectResponse.class),
                expectedDefectResponseBody);
    }
    @Test
    public void deleteDefectTest() {
        String responseBody = defectAdapter.deleteDefect(200, projectCode, id);
        Assert.assertEquals(responseBody, expectedJsonDeleteDefect);
    }
    @Test
    public void updateDefectTest() {

        Defect defect = Defect.builder()
                .severity(4)
                .build();

        DefectResponse expectedDefectResponseBody = DefectResponse
                .builder()
                .result(DefectResult
                        .builder()
                        .id(updateId)
                        .build())
                .build();

        String actualResponseBody = defectAdapter.updateDefect(200, projectCode, updateId, gson.toJson(defect));
        Assert.assertEquals(gson.fromJson(actualResponseBody, DefectResponse.class),
                expectedDefectResponseBody);
    }


}

