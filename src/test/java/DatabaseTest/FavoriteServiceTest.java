package DatabaseTest;

import cn.mentalhealth.domain.Favorite;
import cn.mentalhealth.service.FavoriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FavoriteServiceTest {

    private FavoriteService favoriteService;

    @BeforeEach
    public void setUp() {
        favoriteService = new FavoriteService();
    }
}