import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { SignInComponent } from './views/sign-in/sign-in.component';
import { SignUpComponent } from './views/sign-up/sign-up.component';

const routes: Routes = [
    { path: '', redirectTo: 'signIn', pathMatch: 'full' },
    { path: 'signIn', component: SignInComponent },
    { path: 'signUp', component: SignUpComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }