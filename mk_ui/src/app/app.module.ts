import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ModalModule } from 'ngx-bootstrap/modal';
import { TimepickerModule, BsDatepickerModule } from 'ngx-bootstrap';
import { BsDropdownModule } from 'ngx-bootstrap';

// Import providers
import {
    ApiProvider,
    CommonProvider,
    ExpenseProvider,
    IncomeProvider,
    SettingProvider,
    UserProvider,
    VoucherProvider,
    AccountProvider,
    ReportProvider
} from './providers/provider';

// Import utilities
import { RsaService } from './utilities/utility';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';

import { FormsModule } from '@angular/forms';

import { LayoutComponent } from './views/layout/layout.component';

import { Utils } from '../app/utils';

@NgModule({
    declarations: [
        AppComponent,
        LayoutComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        BsDatepickerModule.forRoot(),
        ModalModule.forRoot(),
        TimepickerModule.forRoot(),
        BsDropdownModule.forRoot()
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
        AccountProvider,
        ReportProvider,
        Utils
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }