import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AccountEditComponent } from './account-edit.component';

const routes: Routes = [
    {
        path: '',
        component: AccountEditComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class AccountEditRoutingModule { }