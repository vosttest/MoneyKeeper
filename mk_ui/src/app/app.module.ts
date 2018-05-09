import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

// Import providers
import {
    ApiProvider,
    UserProvider,
    CommonProvider
} from './providers/providers';

// Import utilities
import { RsaService } from './utils';

import { SignInComponent } from './views/sign-in/sign-in.component';
import { FormsModule } from '@angular/forms';
import { SignUpComponent } from './views/sign-up/sign-up.component';
import { CategoryComponent } from './views/category/category.component';
import { ManageAccountComponent } from './views/manage-account/manage-account.component';
import { ManageAccountAddComponent } from './views/manage-account-add/manage-account-add.component';
import { ManageAccountEditComponent } from './views/manage-account-edit/manage-account-edit.component';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { VoucherComponent } from './views/voucher/voucher.component';
import { IncomeProvider } from './providers/income';

@NgModule({
    declarations: [
        AppComponent,
        SignInComponent,
        SignUpComponent,
        CategoryComponent,
        VoucherComponent,
        ManageAccountComponent,
        ManageAccountAddComponent,
        ManageAccountEditComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        AppRoutingModule,
        BsDatepickerModule.forRoot()
    ],
    providers: [
        ApiProvider,
        UserProvider,
        CommonProvider,
        IncomeProvider,
        RsaService],
    bootstrap: [AppComponent]
})

export class AppModule { }