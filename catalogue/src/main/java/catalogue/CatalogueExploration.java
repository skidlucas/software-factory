package catalogue;

import entities.Cookies;
import javax.ejb.Local;
import java.util.Set;

@Local
public interface CatalogueExploration {

	Set<Cookies> listPreMadeRecipes();

	Set<Cookies> exploreCatalogue(String regexp);

}
