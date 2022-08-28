package adapters;

public class DefectAdapter extends BaseAdapter{
    private static final String ENDPOINT = "defect";

    public String getAllDefects(int statusCode, String projectCode) {
        return get(ENDPOINT + "/" + projectCode, statusCode);
    }
    public String createDefect(int statusCode, String projectCode, String requestBody) {
        return post(ENDPOINT + "/" + projectCode, statusCode, requestBody);
    }
    public String getSpecificDefect(int statusCode, String projectCode, int id) {
        return get(ENDPOINT + "/" + projectCode + "/" + id, statusCode);
    }
    public String deleteDefect(int statusCode, String projectCode, int id) {
        return delete(ENDPOINT + "/" + projectCode + "/" + id, statusCode);
    }
    public String updateDefect(int statusCode, String projectCode, int id, String requestBody) {
        return patch(ENDPOINT + "/" + projectCode + "/" + id, statusCode, requestBody);
    }
}
