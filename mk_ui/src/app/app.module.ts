import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ModalModule } from 'ngx-bootstrap/modal';
import { TimepickerModule } from 'ngx-bootstrap';


// Import providers
import {
    ApiProvider,
    CommonProvider,
    ExpenseProvider,
    IncomeProvider,
    SettingProvider,
    UserProvider,
    VoucherProvider,
    AccountProvider
} from './providers/provider';

// Import utilities
import { RsaService } from './utilities/utility';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { AccountComponent } from './views/account/account.component';
import { AccountAddComponent } from './views/account-add/account-add.component';
import { AccountEditComponent } from './views/account-edit/account-edit.component';
import { SettingComponent } from './views/setting/setting.component';
import { VoucherComponent } from './views/voucher/voucher.component';

import { FormsModule } from '@angular/forms';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';

import { ForgotPasswordComponent } from './views/forgot-password/forgot-password.component';
import { ChangePasswordComponent } from './views/change-password/change-password.component';
import { EqualValidator } from './views/change-password/equal-validator.directive';
import { LayoutComponent } from './views/layout/layout.component';
import { FilterPipe } from './filter.pipe';

@NgModule({
    declarations: [
        AppComponent,
        AccountComponent,
        AccountAddComponent,
        AccountEditComponent,
        SettingComponent,
        VoucherComponent,
        ForgotPasswordComponent,
        ChangePasswordComponent,
        LayoutComponent,
        EqualValidator,
        FilterPipe    
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        BsDatepickerModule.forRoot(),
        ModalModule.forRoot(),
        TimepickerModule.forRoot()
    ],
    providers: [
        ApiProvider,
        CommonProvider,
        ExpenseProvider,
        IncomeProvider,
        SettingProvider,
        UserProvider,
        VoucherProvider,
        RsaService,
        AccountProvider
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }