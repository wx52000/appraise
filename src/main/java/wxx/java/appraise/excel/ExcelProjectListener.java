package wxx.java.appraise.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wxx.java.appraise.entity.ExcelProject;
import wxx.java.appraise.service.ProjectService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ExcelProjectListener extends AnalysisEventListener<ExcelProject> {
    private ProjectService projectService;
    @Autowired
    public void  setProjectService(ProjectService projectService){
        this.projectService = projectService;
    }

    private List<Map> maps = new ArrayList<>();

    @Override
    public void invoke(ExcelProject excelProject, AnalysisContext analysisContext) {
        if (excelProject != null && excelProject.getNumber() != null){
            try {
                List<Map> map;
                map = projectService.addExcel(excelProject);
                if (map.size() >= 1){
                    maps.addAll(map);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


    public List<Map> getMaps() {
        return maps;
    }

    public void setMaps(List<Map> maps) {
        this.maps = maps;
    }

}
