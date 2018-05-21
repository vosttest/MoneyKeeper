import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TimepickerModule, BsDatepickerModule } from 'ngx-bootstrap';
import { ChangePasswordRoutingModule } from './change-password-routing.module';
import { ChangePasswordComponent } from './change-password.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { EqualValidator } from '../../utilities/equal-validator.directive';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        TimepickerModule.forRoot(),
        BsDatepickerModule.forRoot(),
        ModalModule.forRoot(),
        ChangePasswordRoutingModule,
        
    ],
    declarations: [
        ChangePasswordComponent,
        EqualValidator
        
    ]
})

export class ChangePasswordModule { }