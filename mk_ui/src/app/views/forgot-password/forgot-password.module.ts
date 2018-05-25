import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { TimepickerModule, BsDatepickerModule } from 'ngx-bootstrap';

import { ForgotPasswordRoutingModule } from './forgot-password-routing.module';
import { ForgotPasswordComponent } from './forgot-password.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { EqualValidatorForgotPassword } from '../../utilities/utility';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        TimepickerModule.forRoot(),
        BsDatepickerModule.forRoot(),
        ModalModule.forRoot(),
        ForgotPasswordRoutingModule
    ],
    declarations: [
        ForgotPasswordComponent,
        EqualValidatorForgotPassword
    ]
})

export class ForgotPasswordModule { }