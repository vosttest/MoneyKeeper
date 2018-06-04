import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { LayoutComponent } from './views/layout/layout.component';

const routes: Routes = [
    { path: '', redirectTo: 'sign-in', pathMatch: 'full' },
    { path: 'sign-in', loadChildren: './views/sign-in/sign-in.module#SignInModule' },
    { path: 'sign-up', loadChildren: './views/sign-up/sign-up.module#SignUpModule' },
    { path: 'forgot-password', loadChildren: './views/forgot-password/forgot-password.module#ForgotPasswordModule' },
    {
        path: '',
        component: LayoutComponent,
        children:
            [
                {
                    path: 'account/:function',
                    loadChildren: './views/account/account.module#AccountModule'
                }, {
                    path: 'account-add/:function',
                    loadChildren: './views/account-add/account-add.module#AccountAddModule'
                }, {
                    path: 'account-edit/:id',
                    loadChildren: './views/account-edit/account-edit.module#AccountEditModule'
                }, {
                    path: 'dashboard',
                    loadChildren: './views/dashboard/dashboard.module#DashboardModule'
                }, {
                    path: 'category/:function',
                    loadChildren: './views/category/category.module#CategoryModule'
                }, {
                    path: 'setting/:function',
                    loadChildren: './views/setting/setting.module#SettingModule'
                }, {
                    path: 'voucher/:function',
                    loadChildren: './views/voucher/voucher.module#VoucherModule'
                }, {
                    path: 'voucher-edit/:id',
                    loadChildren: './views/voucher-edit/voucher-edit.module#VoucherEditModule'
                }, {
                    path: 'change-password',
                    loadChildren: './views/change-password/change-password.module#ChangePasswordModule'
                }, {
                    path: 'report/:function',
                    loadChildren: './views/report/report.module#ReportModule'
                }, {
                    path: 'profile',
                    loadChildren: './views/profile/profile.module#ProfileModule'
                }, {
                    path: 'log',
                    loadChildren: './views/log/log.module#LogModule'
                }
            ]
    },
    { path: '**', redirectTo: 'sign-in', pathMatch: 'full' },
];

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forRoot(routes, { useHash: true })
    ],
    exports: [RouterModule],
    declarations: []
})

export class AppRoutingModule { }