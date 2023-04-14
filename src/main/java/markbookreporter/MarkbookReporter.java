/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package markbookreporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import markbookreporter.model.AssessmentItem;
import markbookreporter.model.AssessmentItemSection;
import markbookreporter.model.AssessmentItemSectionResult;
import markbookreporter.model.CompletionStatus;
import markbookreporter.model.MarkBook;
import markbookreporter.model.Student;
import markbookreporter.model.curriculum.ContentStrand;
import markbookreporter.model.curriculum.Curriculum;
import markbookreporter.model.curriculum.CurriculumItem;
import markbookreporter.model.curriculum.ProficiencyStrand;
import markbookreporter.reportbuilder.assessresultsreport.AssessmentResultsReportBuilder;
import markbookreporter.reportbuilder.assessresultsreport.AssessmentResultsReportConfiguration;
import markbookreporter.reportbuilder.curricassessreport.CurriculumAssessmentReportBuilder;
import markbookreporter.reportbuilder.curricassessreport.CurriculumAssessmentReportConfiguration;
import markbookreporter.reportbuilder.studentassessmentreport.StudentAssessmentReportBuilder;
import markbookreporter.reportbuilder.studentassessmentreport.StudentAssessmentReportConfiguration;
import markbookreporter.reportbuilder.studentcurricreport.StudentCurriculumReportBuilder;
import markbookreporter.reportbuilder.studentcurricreport.StudentCurriculumReportConfiguration;
import markbookreporter.reportbuilder.studentprogreport.StudentProgressReportBuilder;
import markbookreporter.reportbuilder.studentprogreport.StudentProgressReportConfiguration;
import markbookreporter.velocityutils.JavaUtilLoggingWrapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 *
 * @author General
 */
public class MarkbookReporter {

    private Properties properties;
    private Logger logger = Logger.getLogger(MarkbookReporter.class.getName());
    private SimpleDateFormat assessItemDateFormat;
    
    private List<Curriculum> curriculums = new LinkedList();
    /**
     * The application-global intance of the Velocity Engine
     */
    protected VelocityEngine velocityEngine;
    /*
     * The columns to skip in the spreadsheet to get to the first column 
     * contianing an assessment item section
     */
    private final int assessmentItemSectionColOffset = 3;
    //column indexes for student result data
    private final int studentFirstNameColumn = 1;
    private final int studentLastNameColumn = 0;
    private final int studentSubmittedItemColumn = 2;
    //row number for assessment item section def rows on worksheet def
    private final int curriculumItemForSectionRow = 0;
    private final int profStrandForSectionRow = 1;
    private final int taskArchetypeRow = 2;
    private final int textbookRefForSectionRow = 3;
    private final int totalMarksForSectionRow = 4;
    private final int sectionNameForSectionRow = 5;

    private MarkbookReporter() { 
        //Load the package-internal properties file
        properties = new Properties();
        InputStream input = null;
        try {
            input = MarkbookReporter.class.getResourceAsStream("MarkbookReporter.properties");
            // load a properties file
            properties.load(input);
            //initialise DateFormat
            assessItemDateFormat = new SimpleDateFormat(properties.getProperty("assesItemDateFormat"));
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }   
        //Load the curruculums
        loadCurriculums();
        //Initialise the Velocity system
        /*
         *  create a new instance of the engine
         */
        velocityEngine = new VelocityEngine();
        /*
         *  configure the engine. 
         */
        velocityEngine.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM, new JavaUtilLoggingWrapper());
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath"); 
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());        
        /*
         *  initialize the engine
         */
        velocityEngine.init();
    }
    
    public static MarkbookReporter getInstance() {
        return MarkbookReporter.MarkbookReporterHolder.INSTANCE;
    }

    /**
     * @return the curriculums
     */
    public List<Curriculum> getCurriculums() {
        return curriculums;
    }

    /**
     * @param curriculums the curriculums to set
     */
    public void setCurriculums(List<Curriculum> curriculums) {
        this.curriculums = curriculums;
    }

    /**
     * The application-global intance of the Velocity Engine
     * @return the velocityEngine
     */
    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    /**
     * The application-global intance of the Velocity Engine
     * @param velocityEngine the velocityEngine to set
     */
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
    
    private static class MarkbookReporterHolder {
        private static final MarkbookReporter INSTANCE = new MarkbookReporter();
    }
    
    public String generateAssessmentResultsReport(MarkBook markbook, AssessmentResultsReportConfiguration config){
        AssessmentResultsReportBuilder bldr = new AssessmentResultsReportBuilder();
        String html = bldr.generateReport(markbook, config);
        return html;
    }
    
    public String generateCurriculumAssessmentReport(MarkBook markbook, CurriculumAssessmentReportConfiguration config){
        CurriculumAssessmentReportBuilder bldr = new CurriculumAssessmentReportBuilder();
        String html = bldr.generateReport(markbook, config);
        return html;
    }
    
    public String generateStudentAssessmentReport(MarkBook markbook, StudentAssessmentReportConfiguration config){
        StudentAssessmentReportBuilder bldr = new StudentAssessmentReportBuilder();
        String html = bldr.generateReport(markbook, config);
        return html;
    }
    
    public String generateStudentCurriculumReport(MarkBook markbook, StudentCurriculumReportConfiguration config){
        StudentCurriculumReportBuilder bldr = new StudentCurriculumReportBuilder();
        String html = bldr.generateReport(markbook, config);
        return html;
    }
    
    public String generateStudentProgressReport(MarkBook markbook, StudentProgressReportConfiguration config){
        StudentProgressReportBuilder bldr = new StudentProgressReportBuilder();
        String html = bldr.generateReport(markbook, config);
        return html;
    }

    public static void main(String[] args) {
        for(Curriculum curr : MarkbookReporter.getInstance().getCurriculums()){
            if(curr.getName().equals("ACARA_8_MATH_V7")){
                MarkBook m = MarkbookReporter.getInstance().loadMarkBook(new File("E:\\Teaching Resources\\Applications\\MarkbookReporter\\testing\\8 Math Gradebook - Sem 2.xls"), curr);
                System.out.println(MarkbookReporter.getInstance().generateAssessmentResultsReport(m, new AssessmentResultsReportConfiguration()));
            }
        }

    }
    /**
     * Args:
     * 0 - The input markbook file path
     * 1 - The output directory
     * 2 - The curriculum to apply
     * 3 - The report type
     * @param args the command line arguments
     */
    /*
    public static void init(String[] args) {     
        loadApplicationConfig();
        //Load all curriculums from the config file
        loadCurriculums();
        //expected first argument to be the file, second argument to be the name of the curriculum to use
        //
        //validate file
        File markBookFile = new File(args[0]);
        if (!markBookFile.exists()) {
            logger.log(Level.SEVERE, "The markbook path: " + args[0] + " does not refer to a valid file.");
            System.exit(1);
        }  
        //validate output directory
        File outputDir = new File(args[1]);
        if (! outputDir.exists() || ! outputDir.isDirectory()) {
            logger.log(Level.SEVERE, "The output path: " + args[0] + " does not refer to a valid directory.");
            System.exit(1);
        }         
        //validate and load curriculum
        Curriculum curric = Application.getInstance().getCurriculum(args[2]);
        if(curric == null){
            logger.log(Level.SEVERE, "The curriculum " + args[1] + " does not refer to a valid curriculum on file. "
                    + "Check the /config/curriculum/ directory for a valid curriculum name");
            System.exit(1);            
        }
        //parse the markbook
        MarkBook markBook = loadMarkBook(markBookFile, curric);
        if (markBook == null) {
            logger.log(Level.SEVERE, "No markbook loaded, exiting...");
            System.exit(1);
        }
        //now that we have a Markbook, send it to the relevant report builders   
        ReportType repType = null;
        switch(args[3]){
            case "CA":
                repType = ReportType.CURR_ASSESS_REPORT;
                break;
            case "SA": 
                repType = ReportType.STUD_ASSESS_REPORT;
                break;
            case "SC":
                repType = ReportType.STUD_CURR_REPORT;
                break;
            case "SP":
                repType = ReportType.STUD_PROGRESS_REPORT;
                break;
            case "AR":
                repType = ReportType.ASSESS_RESULT_REPORT;
                break;                
            default:
                logger.log(Level.SEVERE, "Report type " + args[3] + " not recognised. Please check parameters");
                System.exit(1);
               
        }
        ReportBuilder rb = ReportBuilderFactory.createReportBuilder(repType);
        rb.generateReport(markBook, outputDir, curric);        
    }
    */

    private void loadCurriculums() {
        //Load the curriculum definitions
        String curriculumDirPath = properties.getProperty("curriculumFilesDir").replace('/', File.separatorChar);
        File curriculumDir = new File(curriculumDirPath);
        if (!curriculumDir.exists() || !curriculumDir.isDirectory()) {
            throw new RuntimeException("Curriculum directory does not exist at " + curriculumDirPath);
        }
        //iterate the excel files in this directory       
        for (File curriculumFile : FileUtils.listFiles(curriculumDir, new String[]{"xls", "xlsx"}, false)) {
            InputStream inp = null;
            try {
                //load the workbook
                inp = new FileInputStream(curriculumFile);
                HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
                //create a new Curriculum
                Curriculum curriculum = new Curriculum();
                curriculum.setName(FilenameUtils.removeExtension(curriculumFile.getName()));
                //
                //load the Proficiency Strands Worksheet
                //
                Sheet sheet = wb.getSheet(properties.getProperty("curriculumFilePerfStrandSheetName"));
                if (sheet == null) {
                    logger.log(Level.WARNING, "Could not load Performance Strands sheet for workbook: " + curriculumFile.getName());
                    continue;
                }
                //iterate rows, ignoring the first and create and add proficiency strands                                
                Row row = null;
                for (int i = 1; (row = sheet.getRow(i)) != null; i++) {
                    ProficiencyStrand profStrand = new ProficiencyStrand();
                    if (!isValidStringCell(row.getCell(0))) {
                        logger.log(Level.WARNING, "No code for proficiency strand at row " + i + ", skipping");
                        continue;
                    }
                    profStrand.setCode(row.getCell(0).getStringCellValue());
                    profStrand.setName(isValidStringCell(row.getCell(1)) ? row.getCell(1).getStringCellValue() : null);
                    profStrand.setDescription(isValidStringCell(row.getCell(2)) ? row.getCell(2).getStringCellValue() : null);
                    curriculum.getProficiencyStrands().add(profStrand);
                }
                //
                //load the Content Strands Worksheet
                //
                sheet = wb.getSheet(properties.getProperty("curriculumFileContentStrandSheetName"));
                if (sheet == null) {
                    logger.log(Level.WARNING, "Could not load Content Strands sheet for workbook: " + curriculumFile.getName());
                    continue;
                }
                //iterate rows, ignoring the first and create and add proficiency strands                
                row = null;
                for (int i = 1; (row = sheet.getRow(i)) != null; i++) {
                    ContentStrand contentStrand = new ContentStrand();
                    if (!isValidStringCell(row.getCell(0))) {
                        logger.log(Level.WARNING, "No code for content strand at row " + i + ", skipping");
                        continue;
                    }
                    contentStrand.setCode(row.getCell(0).getStringCellValue());
                    contentStrand.setName(isValidStringCell(row.getCell(1)) ? row.getCell(1).getStringCellValue() : null);
                    contentStrand.setDescription(isValidStringCell(row.getCell(2)) ? row.getCell(2).getStringCellValue() : null);
                    curriculum.getContentStrands().add(contentStrand);
                }
                //
                //load the CurriculumItems Worksheet
                //
                sheet = wb.getSheet(properties.getProperty("curriculumFileCurricItemSheetName"));
                if (sheet == null) {
                    logger.log(Level.WARNING, "Could not load Curriculum Items sheet for workbook: " + curriculumFile.getName());
                    continue;
                }
                //iterate rows, ignoring the first and create and add proficiency strands                
                row = null;
                for (int i = 1; (row = sheet.getRow(i)) != null; i++) {
                    CurriculumItem curriculumItem = new CurriculumItem();
                    if (!isValidStringCell(row.getCell(0))) {
                        logger.log(Level.WARNING, "No code for curriculum item at row " + i + ", skipping");
                        continue;
                    }
                    curriculumItem.setCode(row.getCell(0).getStringCellValue());
                    //locate and add the content strand
                    if (!isValidStringCell(row.getCell(1))) {
                        logger.log(Level.WARNING, "No content strand for curriculum item at row " + i + ", skipping");
                        continue;
                    }
                    curriculumItem.setContentStrand(curriculum.getContentStrandByCode(row.getCell(1).getStringCellValue()));
                    //locate and add the parent item if it exists
                    curriculumItem.setParent(isValidStringCell(row.getCell(2)) ? curriculum.getCurriculumItemByCode(row.getCell(2).getStringCellValue()) : null);
                    curriculumItem.setDescription(isValidStringCell(row.getCell(3)) ? row.getCell(3).getStringCellValue() : null);
                    curriculum.getCurriculumItems().add(curriculumItem);
                }
                //attach this curriculum to the application
                this.getCurriculums().add(curriculum);
            } catch (FileNotFoundException ex) {
                logger.log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
            } finally {
                if (inp != null) {
                    try {
                        inp.close();
                    } catch (IOException ex) {
                        logger.log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
    }

    private static boolean isValidStringCell(Cell cell) {
        if(cell == null) return false;
        if(cell.getCellType() != Cell.CELL_TYPE_STRING) return false;
        if(cell.getStringCellValue() == null) return false;
        if(cell.getStringCellValue().trim().length() == 0) return false;
        return true;
    }
    
    private static boolean isValidDateCell(Cell cell) {
        if(cell == null) return false;
        if(cell.getCellType() != Cell.CELL_TYPE_NUMERIC) return false;
        if(! DateUtil.isCellDateFormatted(cell)) return false;
        if(cell.getDateCellValue() == null) return false;
        return true;
    }  
    
    private static boolean isValidNumericCell(Cell cell) {
        if(cell == null) return false;
        if(cell.getCellType() != Cell.CELL_TYPE_NUMERIC) return false;
        if(DateUtil.isCellDateFormatted(cell)) return false;
        if(cell.getNumericCellValue() == Double.NaN) return false;
        return true;
    }    

    /**
     * Load a markbook from the given excel spreadsheet, linking the
     * curriculum items to the given curriculum
     * 
     * @param markBookFile
     * @param curriculum
     * @return An instantiated Markbook
     */
    public MarkBook loadMarkBook(File markBookFile, Curriculum curriculum) {
        InputStream inp = null;
        MarkBook markBook = null;
        try {
            //load the workbook
            inp = new FileInputStream(markBookFile);
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
            //create a new MarkBook
            markBook = new MarkBook();
            markBook.setName(markBookFile.getName());
            markBook.setCurriculum(curriculum);
            logger.log(Level.INFO, "loading markbook: " + markBook.getName());
            //
            //load each worksheet and create a new assessment
            //
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                Sheet sheet = wb.getSheetAt(i);
                logger.log(Level.INFO, "loading sheet: " + sheet.getSheetName());
                if (sheet == null) {
                    logger.log(Level.WARNING, "Could not load sheet " + sheet.getSheetName() + " for workbook: " + markBookFile.getName());
                    continue;
                }
                //validate some basic properties
                StringBuffer errorMsgs = new StringBuffer();
                if(! validateAssessmentItemSheet(sheet, errorMsgs)){
                    logger.log(Level.WARNING, "Current sheet: " + sheet.getSheetName() + " was not a valid assessment item sheet ( " + errorMsgs + "), skipping ");
                    continue;
                }
                //instanitate new assessment item and link to markbook
                AssessmentItem assessmentItem = new AssessmentItem();
                assessmentItem.setName(sheet.getSheetName());
                markBook.getAssessmentItems().add(assessmentItem);
                //pull out the date set and date due fields                
                if(isValidDateCell(sheet.getRow(0).getCell(1))){
                    Date date = sheet.getRow(0).getCell(1).getDateCellValue();
                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(date);
                    assessmentItem.setDateSet(startDate);
                }
                if(isValidDateCell(sheet.getRow(1).getCell(1))){
                    Date date = sheet.getRow(1).getCell(1).getDateCellValue();
                    Calendar dueDate = Calendar.getInstance();
                    dueDate.setTime(date);
                    assessmentItem.setDateSet(dueDate);
                }
                //pull out the Assessment Item Category Title from the sheet
                if(isValidStringCell(sheet.getRow(2).getCell(0))){
                    assessmentItem.setCategoryTitle(sheet.getRow(2).getCell(0).getStringCellValue());
                }
                /////////////////////////////////////////////////////////////////////////
                // Creating assessment item sections and adding grades for each student
                ///////////////////////////////////////////////////////////////////////              
                // Create assessment item sections from row 5
                int colNum = -1;
                for(Cell cell : sheet.getRow(sectionNameForSectionRow)){                    
                    colNum++;
                    //ignore first 3 columns
                    if(colNum < assessmentItemSectionColOffset) continue;
                    //while non-blank cells, add assessment item sections
                    if(! isValidStringCell(cell)) break;
                    AssessmentItemSection assessItemSec = new AssessmentItemSection();
                    assessItemSec.setName(cell.getStringCellValue());                    
                    assessItemSec.setAssessmentItem(assessmentItem);
                    assessmentItem.getAssessmentItemSections().add(assessItemSec);                    
                }
                //iterate rows, linking to sections                
                for(Row row : sheet){   
                    if(row.getRowNum() == curriculumItemForSectionRow){  
                        //the first row, link curriculum items to the sections
                        colNum = assessmentItemSectionColOffset - 1;
                        for(AssessmentItemSection section : assessmentItem.getAssessmentItemSections()){
                            colNum++;
                            String currItemCodeStr = isValidStringCell(row.getCell(colNum)) ? row.getCell(colNum).getStringCellValue() : null;
                            //if no code is specified then dont link
                            if(currItemCodeStr == null) continue;
                            //split the curriculum items by the comman separator
                            //find curriculum items and link
                            for(String currItemCode : currItemCodeStr.split(",")){
                                CurriculumItem currItem = curriculum.getCurriculumItemByCode(currItemCode.trim());
                                if(currItem == null){
                                    logger.log(Level.WARNING, "Couldnt find a curriculum item for code: " + currItemCode + " in assess item section " + section.getName());
                                    continue;
                                }
                                section.getCurriculumItems().add(currItem);
                            }                            
                        }
                    }else if(row.getRowNum() == sectionNameForSectionRow){
                        //already processed
                    }else if(row.getRowNum() == taskArchetypeRow){
                        //add the task archetype to the assess item section
                        colNum = assessmentItemSectionColOffset - 1;
                        for(AssessmentItemSection section : assessmentItem.getAssessmentItemSections()){
                            colNum++;
                            String taskArchetype = isValidStringCell(row.getCell(colNum)) ? row.getCell(colNum).getStringCellValue() : null;
                            //if no code is specified then dont link
                            if(taskArchetype == null) continue;
                            section.setTaskArchetype(taskArchetype);                             
                        }                         
                        //future section perhaps
                    }else if(row.getRowNum() == profStrandForSectionRow){
                        //the second row, link proficiency strands to the sections
                        colNum = assessmentItemSectionColOffset - 1;
                        for(AssessmentItemSection section : assessmentItem.getAssessmentItemSections()){
                            colNum++;
                            String profStrandCodeStr = isValidStringCell(row.getCell(colNum)) ? row.getCell(colNum).getStringCellValue() : null;
                            //if no code is specified then dont link
                            if(profStrandCodeStr == null) continue;
                            //split the curriculum items by the comman separator
                            //find curriculum item and link
                            for(String profStrandCode : profStrandCodeStr.split(",")){
                                ProficiencyStrand profStrand = curriculum.getProficiencyStrandByCode(profStrandCode);
                                if(profStrand == null){
                                    logger.log(Level.WARNING, "Couldnt find a proficiency strand for code: " + profStrand + " in assess item section " + section.getName());
                                    continue;
                                }
                                section.getProficiencyStrands().add(profStrand);
                            }                            
                        }                        
                    }else if(row.getRowNum() == textbookRefForSectionRow){
                        //read the textbook reference for the sections
                        colNum = assessmentItemSectionColOffset - 1;
                        for(AssessmentItemSection section : assessmentItem.getAssessmentItemSections()){
                            colNum++;
                            String textbookRef = isValidStringCell(row.getCell(colNum)) ? row.getCell(colNum).getStringCellValue() : null;
                            //if no code is specified then dont link
                            if(textbookRef == null) continue;
                            section.setTextbookReference(textbookRef);                             
                        }                        
                    } else if(row.getRowNum() == totalMarksForSectionRow){
                        //read the total marks for the sections
                        colNum = assessmentItemSectionColOffset - 1;
                        for(AssessmentItemSection section : assessmentItem.getAssessmentItemSections()){
                            colNum++;
                            Double totalMarks = isValidNumericCell(row.getCell(colNum)) ? row.getCell(colNum).getNumericCellValue() : null;  
                            if(totalMarks == null){
                                logger.log(Level.WARNING, "Total marks for section " + section.getName() + " was not specified");
                            }
                            //yes, the total marks could be null
                            section.setTotalMarks(totalMarks);
                        }                          
                    }else{
                        //process the remaining row on the sheet, these are the student results rows
                        //
                        //locate the student, or create
                        String firstName = isValidStringCell(row.getCell(studentFirstNameColumn)) ? row.getCell(studentFirstNameColumn).getStringCellValue() : null;
                        String lastName = isValidStringCell(row.getCell(studentLastNameColumn)) ? row.getCell(studentLastNameColumn).getStringCellValue() : null;
                        if(firstName == null && lastName == null){
                            logger.log(Level.INFO, "invalid student name for row " + row.getRowNum() + " , skipping");
                            continue;
                        }
                        Student student = markBook.getOrCreateStudent(firstName, lastName);
                        //for each assessmentitemSection, add a result
                        //by this process, every student will get a result for eah assesment item section,
                        //even is there is no data in the row (in which case, each assessmentItem|SetionResult
                        //will have an achieved mark of zero and a completion status of 'not submitted')
                        //
                        //It is up to the report generator to decide how to report on this, ie saying that 
                        //an assessment item is not submitted by cheching that each result has completion status
                        //'not submitted'
                        colNum = assessmentItemSectionColOffset - 1;
                        for(AssessmentItemSection section : assessmentItem.getAssessmentItemSections()){
                            colNum++;
                            //create a new result for this section under the current assessment item
                            AssessmentItemSectionResult assessItemSectionResult = new AssessmentItemSectionResult();
                            //set the marks given if the data is there                            
                            if(isValidNumericCell(row.getCell(colNum))){
                                assessItemSectionResult.setMarksAttained(row.getCell(colNum).getNumericCellValue());
                            }else{
                                assessItemSectionResult.setMarksAttained((double)0);
                            }                 
                            //determine the completion status of the assement item result
                            if(row.getCell(colNum) == null){
                                //left blank means not attempted
                                assessItemSectionResult.setCompletionStatus(CompletionStatus.NOT_ATTEMPTED);                             
                            }else{
                                //theres something in the cell, use that to determine the completion status
                                switch(row.getCell(colNum).getCellType()){
                                    case Cell.CELL_TYPE_NUMERIC:
                                        //if numeric (not blank) then it was attempted, even if the mark is a 0
                                        assessItemSectionResult.setCompletionStatus(CompletionStatus.ATTEMPTED);
                                        break;
                                    case Cell.CELL_TYPE_STRING:
                                        //we have a code;
                                        String completionStatusStr = row.getCell(colNum).getStringCellValue();
                                        if(completionStatusStr.equalsIgnoreCase("n")){
                                            //'n' means 'not attempted'
                                            assessItemSectionResult.setCompletionStatus(CompletionStatus.NOT_ATTEMPTED);
                                        }else if(completionStatusStr.equalsIgnoreCase("r")){
                                            //'r' means 'removed from assessment'
                                            assessItemSectionResult.setCompletionStatus(CompletionStatus.REMOVED_FROM_ASSESSMENT);
                                        }else{
                                            //dont know! set as not attempted and warn
                                            logger.log(Level.WARNING, "could not determine the completion status for result in item " + assessmentItem + " section " + section.getName() +" for student " + student);
                                            assessItemSectionResult.setCompletionStatus(CompletionStatus.NOT_ATTEMPTED);
                                        }                                    
                                        break;
                                    case Cell.CELL_TYPE_BLANK:
                                        //left blank means not attempted
                                        assessItemSectionResult.setCompletionStatus(CompletionStatus.NOT_ATTEMPTED);
                                        break;
                                    default:
                                        //dont know! set as not attempted and warn
                                        logger.log(Level.WARNING, "could not determine the completion status for result in item " + assessmentItem + " section " + section.getName() +" for student " + student);
                                        assessItemSectionResult.setCompletionStatus(CompletionStatus.NOT_ATTEMPTED);                                                                        
                                }  
                            }
                            //link to the student and section
                            assessItemSectionResult.setAssessmentItemSection(section);
                            assessItemSectionResult.setStudent(student);
                            student.getAssessmentItemSectionResults().add(assessItemSectionResult);
                            section.getAssessmentItemSectionResults().add(assessItemSectionResult);                                                        
                        }                        
                    }                    
                }
            }            
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            if (inp != null) {
                try {
                    inp.close();
                } catch (IOException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
        return markBook;
    }

    /**
     * examines the worksheet for parse suitability, appending errors tot he supplied string
     * @param sheet the sheet to examine
     * @param errors the error log string
     * @return 
     */
    private static boolean validateAssessmentItemSheet(Sheet sheet, StringBuffer errors) {
        //if the sheet name has a ! in its name, idicates we need to skip
        if(sheet.getSheetName().contains("!")){
            errors.append("Sheetname contained a ! symbol");
            return false;
        }
        boolean isValidSheet = true;
        //must have at least 7 rows
        int count = 0;
        for(Row r : sheet){
            count++;
        }
        if(count < 7){
            errors.append("Sheet has less than 7 rows. ");
            isValidSheet = false;
        }
        //must have at least 4 columns in the first 6 rows
        /*
        count = 0;
        for(Row r : sheet){
            if(count >= 6) break;
            if(r.getCell(3) == null) {
                errors.append("Row " + r.getRowNum() + " did not have 4 columns of data.");
                isValidSheet = false;
            }  
            count++;
        }
        */ 
        return isValidSheet;
        
    }

    /*
    private static void loadApplicationConfig() {
        ApplicationConfiguration applicationConfig = new ApplicationConfiguration();
        InputStream input = null;
        try {
            File configFile = new File(properties.getProperty("configFileLocation").replace('/', File.separatorChar));
            if(! configFile.exists()){
                logger.log(Level.WARNING, "config file not found at " + configFile.getCanonicalPath() + ", using defaults");
            }else{
                input = new FileInputStream(configFile);
                // load a properties file
                Properties acProps = new Properties();
                acProps.load(input);
                applicationConfig = new ApplicationConfiguration(acProps);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Application.getInstance().setApplicationConfig(applicationConfig);
    }
    */
}

