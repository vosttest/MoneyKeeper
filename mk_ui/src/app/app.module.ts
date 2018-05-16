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

import { FormsModule } from '@angular/forms';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';

import { LayoutComponent } from './views/layout/layout.component';

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