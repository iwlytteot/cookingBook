import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RecipeServiceComponent } from './recipe-service/recipe-service.component';
import { IngredientServiceComponent } from './ingredient-service/ingredient-service.component';
import { RecipeComponent } from './recipe/recipe.component';

import {DataViewModule} from 'primeng/dataview';

@NgModule({
  declarations: [
    AppComponent,
    RecipeServiceComponent,
    IngredientServiceComponent,
    RecipeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    DataViewModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
