package mangobomb.bombermango;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public class GenerateFactory implements EntityFactory {
    @Spawns("Rocket")
    public Entity abc(SpawnData data){
        return FXGL.entityBuilder(data)
                .at(100,100)
                .view(new Rectangle(100,100))
                .with(new ProjectileComponent(new Point2D(1,0), 100))
                .build();
    }
}
