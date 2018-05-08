import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

// Import providers
import {
    ApiProvider,
    UserProvider
} from './providers/providers';

// Import utilities
import { RsaService } from './utils';

import { SignInComponent } from './views/sign-in/sign-in.component';
import { FormsModule } from '@angular/forms';
import { SignUpComponent } from './views/sign-up/sign-up.component';
import { CategoryComponent } from './views/category/category.component';

@NgModule({
    declarations: [
        AppComponent,
        SignInComponent,
        SignUpComponent,
        CategoryComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        AppRoutingModule
    ],
    providers: [
        ApiProvider,
        UserProvider,
        RsaService]
    bootstrap: [AppComponent]
})

export class AppModule { }