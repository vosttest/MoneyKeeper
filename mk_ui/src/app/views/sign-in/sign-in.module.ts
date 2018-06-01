import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { TimepickerModule, BsDatepickerModule } from 'ngx-bootstrap';
import { ModalModule } from 'ngx-bootstrap/modal';

import { SignInRoutingModule } from './sign-in-routing.module';
import { SignInComponent } from './sign-in.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        TimepickerModule.forRoot(),
        BsDatepickerModule.forRoot(),
        ModalModule.forRoot(),
        SignInRoutingModule
    ],
    declarations: [
        SignInComponent
    ]
})

export class SignInModule { }