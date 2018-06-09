package myshift.com;

public class DailyInfo {

    private String dayInMonth;
    private String jobName;
    private String totalHours;
    private String totalDaily;

    public DailyInfo(String dayInMonth, String jobName, String totalHours, String totalDaily) {
        this.dayInMonth = dayInMonth;
        this.jobName = jobName;
        this.totalHours = totalHours;
        this.totalDaily = totalDaily;
    }

    public String getDayInMonth() {
        return dayInMonth;
    }

    public void setDayInMonth(String dayInMonth) {
        this.dayInMonth = dayInMonth;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public String getTotalDaily() {
        return totalDaily;
    }

    public void setTotalDaily(String totalDaily) {
        this.totalDaily = totalDaily;
    }
}
