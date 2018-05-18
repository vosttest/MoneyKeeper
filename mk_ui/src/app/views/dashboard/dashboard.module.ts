import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { TimepickerModule } from 'ngx-bootstrap';
import { BsDatepickerModule } from 'ngx-bootstrap';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { FilterPipeDashBoard } from '../../utilities/filter.pipe';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        TimepickerModule.forRoot(),
        BsDatepickerModule.forRoot(),
        ModalModule.forRoot(),
        DashboardRoutingModule
    ],
    declarations: [
        DashboardComponent,
        FilterPipeDashBoard
    ]
})

export class DashboardModule { }