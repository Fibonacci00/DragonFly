package com.kutayacaar.bird;

import static java.awt.Color.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.Color;
import java.util.BitSet;
import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {
SpriteBatch batch;
Texture background;
Texture dragon;
	Texture bullet1;
	Texture bullet2;
	Texture bullet3;
Texture bullet4;

float dragonX = 0;
float dragonY = 0;
	int gameState  = 0 ;
	float velocity = 0 ;
	float gravity = 0.5f;//beğenmezsen değiştir yerçekimi
float enemyVelocity = 2;
Random random;

int score = 0;
int scoredEnemy = 0;
	int numberOfEnemies=4;
	float[] enemyX = new float[numberOfEnemies];
	float[] enemyOffSet = new float[numberOfEnemies];
	float[] enemyOffSet2 = new float[numberOfEnemies];
	float[] enemyOffSet3 = new float[numberOfEnemies];
float[] enemyOffSet4 = new float[numberOfEnemies];
float distance = 0;

ShapeRenderer shapeRenderer;
Circle dragonCircle;
BitmapFont font;
BitmapFont font2;

	Circle[] enemyCircles;
	Circle[] enemyCircles2;
	Circle[] enemyCircles3;
	Circle[] enemyCircles4;


	@Override
	public void create () {

font = new BitmapFont();
font.setColor(com.badlogic.gdx.graphics.Color.WHITE);
font.getData().setScale(7);
font2 = new BitmapFont();
font2.setColor(com.badlogic.gdx.graphics.Color.WHITE);
font2.getData().setScale(7);
batch = new SpriteBatch();
background = new Texture("background.png");
dragon = new Texture("dragon.png");


	dragonX =Gdx.graphics.getWidth()/2-dragon.getHeight()/2;
		 dragonY =Gdx.graphics.getHeight()/3;
		 distance = Gdx.graphics.getWidth()/2;
		 dragonCircle = new Circle();

		 enemyCircles = new Circle[numberOfEnemies];
		enemyCircles2 = new Circle[numberOfEnemies];
		enemyCircles3 = new Circle[numberOfEnemies];
		enemyCircles4 = new Circle[numberOfEnemies];

		random = new Random();

shapeRenderer = new ShapeRenderer();

		bullet1 = new Texture("bullet.png");
		bullet2 = new Texture("bullet.png");
		bullet3 = new Texture("bullet.png");
bullet4=new Texture("bullet.png");


		for (int i = 0 ; i<numberOfEnemies;i++){
enemyOffSet[i]= (random.nextFloat()) * (Gdx.graphics.getHeight());
			enemyOffSet2[i]= (random.nextFloat()) * (Gdx.graphics.getHeight());
			enemyOffSet3[i]= (random.nextFloat()) * (Gdx.graphics.getHeight());
enemyOffSet4[i] = (random.nextFloat())*(Gdx.graphics.getHeight());
enemyX[i]= Gdx.graphics.getWidth()-bullet1.getWidth()/2+ i*distance;


enemyCircles[i]= new Circle();
			enemyCircles2[i]= new Circle();
			enemyCircles3[i]= new Circle();
			enemyCircles4[i]= new Circle();


		}
	}


	@Override
	public void render () {

		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		if (gameState == 1) {
if (enemyX[scoredEnemy]<Gdx.graphics.getWidth()/2-dragon.getHeight()/2){

	score++;
	if (scoredEnemy<numberOfEnemies-1){
		scoredEnemy++;
	}else {
		scoredEnemy=0;
	}
}
			if (Gdx.input.justTouched()) {

				velocity = -11; //hız

			}
			for (int i = 0; i < numberOfEnemies; i++) {

				if (enemyX[i] < bullet1.getWidth() / Gdx.graphics.getWidth() / 12) {
					enemyX[i] = enemyX[i] + numberOfEnemies * distance;

					enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
					enemyOffSet4[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);


				} else {
					enemyX[i] = enemyX[i] - enemyVelocity;

				}

				enemyX[i] = enemyX[i] - enemyVelocity;

				batch.draw(bullet1, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
				batch.draw(bullet2, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet2[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
				batch.draw(bullet3, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet3[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
				batch.draw(bullet4, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet4[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);

				enemyCircles[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30, Gdx.graphics.getHeight() / 2 + enemyOffSet[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);
				enemyCircles2[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30, Gdx.graphics.getHeight() / 2 + enemyOffSet2[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);
				enemyCircles3[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30, Gdx.graphics.getHeight() / 2 + enemyOffSet3[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);
				enemyCircles4[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth() / 30, Gdx.graphics.getHeight() / 2 + enemyOffSet4[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);

			}

			if (dragonY > 0  ) {

				velocity = velocity + gravity;
				dragonY = dragonY - velocity;
				if (dragonY>= Gdx.graphics.getHeight()){
					dragonY=Gdx.graphics.getHeight()-80;
				}
			}else {
				gameState=2;
			}
		} else if (gameState == 0) {

			if (Gdx.input.justTouched()) {

				gameState = 1;
			}
		} else if (gameState == 2) {
			font2.draw(batch,"Game Over! Tap to play again!",100,Gdx.graphics.getHeight()/2);
			if (Gdx.input.justTouched()) {
				gameState = 1;
				dragonY = Gdx.graphics.getHeight() / 3;
				for (int i = 0; i < numberOfEnemies; i++) {
					enemyOffSet[i] = (random.nextFloat()) * (Gdx.graphics.getHeight());
					enemyOffSet2[i] = (random.nextFloat()) * (Gdx.graphics.getHeight());
					enemyOffSet3[i] = (random.nextFloat()) * (Gdx.graphics.getHeight());
					enemyOffSet4[i] = (random.nextFloat()) * (Gdx.graphics.getHeight());
					enemyX[i] = Gdx.graphics.getWidth() - bullet1.getWidth() / 2 + i * distance;

					enemyCircles[i] = new Circle();
					enemyCircles2[i] = new Circle();
					enemyCircles3[i] = new Circle();
					enemyCircles4[i] = new Circle();


				}
velocity = 0 ;
				scoredEnemy=0;
				score = 0 ;
			}
		}
			batch.draw(dragon, dragonX, dragonY, Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
		font.draw(batch,String.valueOf(score),200,800);

			batch.end();

			dragonCircle.set(dragonX + Gdx.graphics.getWidth() / 30, dragonY + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);

			//shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			//shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.BLACK);
			//shapeRenderer.circle(dragonCircle.x, dragonCircle.y, dragonCircle.radius);
			for (int i = 0; i < numberOfEnemies; i++) {
				//shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 30, Gdx.graphics.getHeight() / 2 + enemyOffSet[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);
				//shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 30, Gdx.graphics.getHeight() / 2 + enemyOffSet2[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);
				//shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 30, Gdx.graphics.getHeight() / 2 + enemyOffSet3[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);
				//shapeRenderer.circle(enemyX[i] + Gdx.graphics.getWidth() / 30, Gdx.graphics.getHeight() / 2 + enemyOffSet4[i] + Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth() / 30);

				if (Intersector.overlaps(dragonCircle, enemyCircles[i]) || Intersector.overlaps(dragonCircle, enemyCircles2[i]) || Intersector.overlaps(dragonCircle, enemyCircles3[i])) {

					gameState = 2;
				}

			}
			shapeRenderer.end();

		}

		@Override
		public void dispose () {

		}
	}

