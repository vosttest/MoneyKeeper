import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

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

@NgModule({
    declarations: [
        AppComponent,
        SignInComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutingModule
    ],
    providers: [
        ApiProvider,
        UserProvider,
        RsaService],
    bootstrap: [AppComponent]
})

export class AppModule { }