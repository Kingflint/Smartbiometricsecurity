package com.flint.smartsecurity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.List;

public class smartsecurity extends ApplicationAdapter {
    private Stage stage;
    private IDataManager dataManager;

    public smartsecurity(IDataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);

        List<AccessRecord> records = dataManager.getAccessRecords();
        for (AccessRecord record : records) {
            String accessInfo = String.format("User: %s | Last Access: %d | Duration: %d",
                    record.getUserId(),
                    record.getTimestamp(),
                    record.getDuration());

            Label label = new Label(accessInfo, skin);
            label.setAlignment(Align.left);
            table.add(label).row();
        }

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        if (dataManager != null) {
            dataManager.close();
        }
    }
}
