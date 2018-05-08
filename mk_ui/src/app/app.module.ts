import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { SocialLoginModule, AuthServiceConfig, GoogleLoginProvider, FacebookLoginProvider } from "angular5-social-login";

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

@NgModule({
    declarations: [
        AppComponent,
        SignInComponent,
        SignUpComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        SocialLoginModule,
        AppRoutingModule
    ],
    providers: [
        ApiProvider,
        UserProvider,
        RsaService,
        {
            provide: AuthServiceConfig,
            useFactory: getAuthServiceConfigs
        }
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }

export function getAuthServiceConfigs() {
    let config = new AuthServiceConfig(
        [
            {
                id: FacebookLoginProvider.PROVIDER_ID,
                provider: new FacebookLoginProvider("364101347416669")
            },
            {
                id: GoogleLoginProvider.PROVIDER_ID,
                provider: new GoogleLoginProvider("981952863337-nbg8mssjs63keggn1562ssecv38c1kv0.apps.googleusercontent.com")
            }
        ]
    );
    return config;
}