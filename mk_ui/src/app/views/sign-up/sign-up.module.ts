import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { TimepickerModule, BsDatepickerModule } from 'ngx-bootstrap';

import { SignInRoutingModule } from './sign-up-routing.module';
import { SignUpComponent } from './sign-up.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { FilterPipeSingUp, EqualValidatorSignUp } from '../../utilities/utility';

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
        SignUpComponent,
        FilterPipeSingUp,
        EqualValidatorSignUp
    ]
})

export class SignUpModule { }