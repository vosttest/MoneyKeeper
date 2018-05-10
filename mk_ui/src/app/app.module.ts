import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ModalModule } from 'ngx-bootstrap/modal';

// Import providers
import {
    ApiProvider,
    CommonProvider,
    ExpenseProvider,
    IncomeProvider,
    SettingProvider,
    UserProvider
} from './providers/provider';

// Import utilities
import { RsaService } from './utilities/utility';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { AccountComponent } from './views/account/account.component';
import { AccountAddComponent } from './views/account-add/account-add.component';
import { AccountEditComponent } from './views/account-edit/account-edit.component';
import { CategoryComponent } from './views/category/category.component';
import { DashboardComponent } from './views/dashboard/dashboard.component';
import { SettingComponent } from './views/setting/setting.component';
import { SignInComponent } from './views/sign-in/sign-in.component';
import { SignUpComponent } from './views/sign-up/sign-up.component';
import { VoucherComponent } from './views/voucher/voucher.component';

import { FormsModule } from '@angular/forms';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';

import { ForgotPasswordComponent } from './views/forgot-password/forgot-password.component';
import { ChangePasswordComponent } from './views/change-password/change-password.component';

@NgModule({
    declarations: [
        AppComponent,
        AccountComponent,
        AccountAddComponent,
        AccountEditComponent,
        CategoryComponent,
        DashboardComponent,
        SettingComponent,
        SignInComponent,
        SignUpComponent,
        VoucherComponent,
        ForgotPasswordComponent,
        ChangePasswordComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        BsDatepickerModule.forRoot(),
        ModalModule.forRoot()
    ],
    providers: [
        ApiProvider,
        CommonProvider,
        ExpenseProvider,
        IncomeProvider,
        SettingProvider,
        UserProvider,
        RsaService
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }