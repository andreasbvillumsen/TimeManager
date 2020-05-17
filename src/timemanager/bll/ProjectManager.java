package timemanager.bll;

import java.io.IOException;
import java.util.ArrayList;
import timemanager.be.Project;
import timemanager.dal.ProjectManagerFacade;
import timemanager.dal.database.ProjectManagerDBDAO;

/**
 *
 * @author andreasvillumsen
 */
public class ProjectManager {
    public ArrayList<Project> projects = new ArrayList<>();
    
    ProjectManagerFacade pm;

    /**
     * ProjectManager Constructor
     * @throws IOException 
     */
    public ProjectManager() throws IOException {
        pm = new ProjectManagerDBDAO();
    }
    
    /**
     * Get all the projects from DAL
     * @return projects
     */
    public ArrayList<Project> getProjects() {
        projects = pm.getProjects();
        return projects;
    }
    
    /**
     * Get projects for a client from DAL
     * @return projects
     */
    public ArrayList<Project> getClientProjects(int clientId) {
        return pm.getClientProjects(clientId);
    }
    
    /**
     * Get a project
     * @param project
     * @return project
     */
    public Project getProject(Project project) {
        return pm.getProject(project);
    }
    
    /**
     * Store a new project
     * @param project
     * @return boolean
     */
    public boolean storeProject(Project project) {
        return pm.storeProject(project);
    }
    
    /**
     * Update a given project
     * @param project
     * @return boolean
     */
    public boolean updateProject(Project project) {
        return pm.updateProject(project);
    }
    
    /**
     * Delete a given project
     * @param project
     * @return boolean
     */
    public boolean deleteProject(Project project) {
        return pm.deleteProject(project);
    }
    
}