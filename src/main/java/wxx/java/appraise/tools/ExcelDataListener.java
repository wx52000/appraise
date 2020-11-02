package wxx.java.appraise.tools;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wxx.java.appraise.entity.ExcelData;
import wxx.java.appraise.service.DepartmentService;
import wxx.java.appraise.service.TechnologyService;
import wxx.java.appraise.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component

public class ExcelDataListener extends AnalysisEventListener<ExcelData> {
    private UserService userService;
    private DepartmentService departmentService;
    private TechnologyService technologyService;
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    @Autowired
    public void setDepartmentService(DepartmentService departmentService){
        this.departmentService = departmentService;
    }
    @Autowired
    public void setTechnologyService(TechnologyService technologyService){
        this.technologyService = technologyService;
    }

    private List<Integer> list = new ArrayList<>();

    private List<ExcelData> listData = new ArrayList<>();

    private Set<Integer> set = new HashSet<>();


    @Override
    public void invoke(ExcelData excelData, AnalysisContext analysisContext) {
        excelData.setDepartment(departmentService.queryName(excelData.getDepartment()).toString());
        if (excelData.getTechnology() != null & excelData.getTechnology() != ""){
            excelData.setTechnology(technologyService.queryByName(excelData.getTechnology()).toString());
        }else {
            excelData.setTechnology(technologyService.query(Integer.valueOf(excelData.getDepartment())).get(0).getId().toString());
        }
        set.add(Integer.valueOf(excelData.getTechnology()));
        listData.add(excelData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        userService.addExcel(listData);
        list = userService.queryByUsername(listData);
    }

    public List<Integer> getList() {
        return list;
    }

    public Set<Integer> getSet() {
        return set;
    }

    public void clear(){
        list.clear();
        listData.clear();
    }
}
