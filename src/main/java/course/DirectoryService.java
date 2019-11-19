package course;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

@Local
public interface DirectoryService {
    public Map<String, Object> getHomeDir();
}
