import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { TimepickerModule, BsDatepickerModule } from 'ngx-bootstrap';

import { ReportRoutingModule } from './report-routing.module';
import { ReportComponent } from './report.component';
import { ModalModule } from 'ngx-bootstrap/modal';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        TimepickerModule.forRoot(),
        BsDatepickerModule.forRoot(),
        ModalModule.forRoot(),
        ReportRoutingModule
    ],
    declarations: [
        ReportComponent
    ]
})

export class ReportModule { }