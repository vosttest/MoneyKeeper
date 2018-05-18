import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ProfileUserComponent } from './profile-user.component';

const routes: Routes = [
    {
        path: '',
        component: ProfileUserComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class ProfileUserRoutingModule { }