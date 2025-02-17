USE [Exam]
GO
/****** Object:  Table [dbo].[admins]    Script Date: 06-01-2025 19:13:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[admins](
	[admin_id] [int] IDENTITY(1,1) NOT NULL,
	[contact] [varchar](255) NULL,
	[firstname] [varchar](255) NULL,
	[lastname] [varchar](255) NULL,
	[user_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[admin_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UKpiovo1hsx7hi5f9ax85epqya9] UNIQUE NONCLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category]    Script Date: 06-01-2025 19:13:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[category_id] [int] IDENTITY(1,1) NOT NULL,
	[category_name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[difficulty]    Script Date: 06-01-2025 19:13:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[difficulty](
	[level_id] [int] IDENTITY(1,1) NOT NULL,
	[level_name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[level_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[enrollment]    Script Date: 06-01-2025 19:13:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[enrollment](
	[enrollment_id] [int] IDENTITY(1,1) NOT NULL,
	[attempted_on] [datetime2](6) NULL,
	[created_on] [datetime2](6) NOT NULL,
	[result] [varchar](255) NOT NULL,
	[status] [varchar](255) NOT NULL,
	[total_marks] [float] NOT NULL,
	[updated_on] [datetime2](6) NULL,
	[created_by] [int] NOT NULL,
	[exam_id] [int] NOT NULL,
	[student_id] [int] NULL,
	[updated_by] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[enrollment_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [unique_student_exam] UNIQUE NONCLUSTERED 
(
	[student_id] ASC,
	[exam_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[exam]    Script Date: 06-01-2025 19:13:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[exam](
	[exam_id] [int] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](6) NULL,
	[duration] [int] NOT NULL,
	[instructions] [varchar](255) NOT NULL,
	[passing_marks] [int] NOT NULL,
	[start_time] [datetime2](6) NOT NULL,
	[title] [varchar](255) NOT NULL,
	[total_marks] [int] NOT NULL,
	[updated_at] [datetime2](6) NULL,
	[created_by] [int] NOT NULL,
	[institute_id] [int] NULL,
	[updated_by] [int] NULL,
	[exam_status] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[exam_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[exam_question]    Script Date: 06-01-2025 19:13:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[exam_question](
	[exam_id] [int] NOT NULL,
	[question_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[exam_id] ASC,
	[question_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[institute]    Script Date: 06-01-2025 19:13:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[institute](
	[institute_id] [int] IDENTITY(1,1) NOT NULL,
	[address] [varchar](255) NULL,
	[contact] [varchar](255) NULL,
	[institute_name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[institute_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[options]    Script Date: 06-01-2025 19:13:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[options](
	[option_id] [int] IDENTITY(1,1) NOT NULL,
	[option_text] [varchar](255) NULL,
	[question_id] [int] NOT NULL,
	[is_correct] [bit] NOT NULL,
 CONSTRAINT [PK_option] PRIMARY KEY CLUSTERED 
(
	[option_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[program_ans]    Script Date: 06-01-2025 19:13:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[program_ans](
	[ans_id] [int] IDENTITY(1,1) NOT NULL,
	[ans_text] [varchar](255) NULL,
	[question_id] [int] NOT NULL,
 CONSTRAINT [PK_program_ans] PRIMARY KEY CLUSTERED 
(
	[ans_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[question_bank]    Script Date: 06-01-2025 19:13:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[question_bank](
	[question_id] [int] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](6) NOT NULL,
	[marks] [float] NULL,
	[question_text] [varchar](255) NULL,
	[updated_at] [datetime2](6) NULL,
	[category] [int] NOT NULL,
	[created_by] [int] NULL,
	[difficulty] [int] NOT NULL,
	[type] [int] NOT NULL,
	[updated_by] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[question_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[question_type]    Script Date: 06-01-2025 19:13:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[question_type](
	[type_id] [int] IDENTITY(1,1) NOT NULL,
	[type_name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[type_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[student]    Script Date: 06-01-2025 19:13:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[student](
	[institute_id] [int] NOT NULL,
	[student_id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NOT NULL,
	[contact] [varchar](255) NULL,
	[firstname] [varchar](255) NULL,
	[lastname] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[student_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UKbkix9btnoi1n917ll7bplkvg5] UNIQUE NONCLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[student_answer]    Script Date: 06-01-2025 19:13:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[student_answer](
	[question_id] [int] NOT NULL,
	[student_id] [int] NOT NULL,
	[answer_text] [text] NULL,
	[marks_earned] [float] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[question_id] ASC,
	[student_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 06-01-2025 19:13:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[user_id] [int] IDENTITY(1,1) NOT NULL,
	[email] [nvarchar](255) NOT NULL,
	[password] [nvarchar](255) NOT NULL,
	[role] [varchar](255) NOT NULL,
 CONSTRAINT [PK__Users__B9BE370F5191B2F7] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UQ__Users__AB6E6164D6111A75] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[admins]  WITH CHECK ADD  CONSTRAINT [FKgc8dtql9mkq268detxiox7fpm] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[admins] CHECK CONSTRAINT [FKgc8dtql9mkq268detxiox7fpm]
GO
ALTER TABLE [dbo].[enrollment]  WITH CHECK ADD  CONSTRAINT [FKdkej6yti8rquhd0039sw9y48j] FOREIGN KEY([created_by])
REFERENCES [dbo].[admins] ([admin_id])
GO
ALTER TABLE [dbo].[enrollment] CHECK CONSTRAINT [FKdkej6yti8rquhd0039sw9y48j]
GO
ALTER TABLE [dbo].[enrollment]  WITH CHECK ADD  CONSTRAINT [FKi3km1i6fn7582ftgnlcoi7ucp] FOREIGN KEY([updated_by])
REFERENCES [dbo].[admins] ([admin_id])
GO
ALTER TABLE [dbo].[enrollment] CHECK CONSTRAINT [FKi3km1i6fn7582ftgnlcoi7ucp]
GO
ALTER TABLE [dbo].[enrollment]  WITH CHECK ADD  CONSTRAINT [FKio7fsy3vhvfgv7c0gjk15nyk4] FOREIGN KEY([student_id])
REFERENCES [dbo].[student] ([student_id])
GO
ALTER TABLE [dbo].[enrollment] CHECK CONSTRAINT [FKio7fsy3vhvfgv7c0gjk15nyk4]
GO
ALTER TABLE [dbo].[enrollment]  WITH CHECK ADD  CONSTRAINT [FKm49p5w5fid6dla0g2rgar018w] FOREIGN KEY([exam_id])
REFERENCES [dbo].[exam] ([exam_id])
GO
ALTER TABLE [dbo].[enrollment] CHECK CONSTRAINT [FKm49p5w5fid6dla0g2rgar018w]
GO
ALTER TABLE [dbo].[exam]  WITH CHECK ADD  CONSTRAINT [FK8mae0t0qflqe6t02weidav818] FOREIGN KEY([updated_by])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[exam] CHECK CONSTRAINT [FK8mae0t0qflqe6t02weidav818]
GO
ALTER TABLE [dbo].[exam]  WITH CHECK ADD  CONSTRAINT [FKhn1p0p5fcpbljp3xvbuxxmq27] FOREIGN KEY([institute_id])
REFERENCES [dbo].[institute] ([institute_id])
GO
ALTER TABLE [dbo].[exam] CHECK CONSTRAINT [FKhn1p0p5fcpbljp3xvbuxxmq27]
GO
ALTER TABLE [dbo].[exam]  WITH CHECK ADD  CONSTRAINT [FKpvl69735airphwhxp59qhvhvb] FOREIGN KEY([created_by])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[exam] CHECK CONSTRAINT [FKpvl69735airphwhxp59qhvhvb]
GO
ALTER TABLE [dbo].[exam_question]  WITH CHECK ADD  CONSTRAINT [FK75y5n4rt15oyfmshrwwi73d] FOREIGN KEY([exam_id])
REFERENCES [dbo].[exam] ([exam_id])
GO
ALTER TABLE [dbo].[exam_question] CHECK CONSTRAINT [FK75y5n4rt15oyfmshrwwi73d]
GO
ALTER TABLE [dbo].[exam_question]  WITH CHECK ADD  CONSTRAINT [FKtrwrv2gj3cya4ipcgnm6v70vy] FOREIGN KEY([question_id])
REFERENCES [dbo].[question_bank] ([question_id])
GO
ALTER TABLE [dbo].[exam_question] CHECK CONSTRAINT [FKtrwrv2gj3cya4ipcgnm6v70vy]
GO
ALTER TABLE [dbo].[options]  WITH CHECK ADD  CONSTRAINT [FK_options_question_bank] FOREIGN KEY([question_id])
REFERENCES [dbo].[question_bank] ([question_id])
GO
ALTER TABLE [dbo].[options] CHECK CONSTRAINT [FK_options_question_bank]
GO
ALTER TABLE [dbo].[program_ans]  WITH CHECK ADD  CONSTRAINT [FK_program_ans_question_bank] FOREIGN KEY([question_id])
REFERENCES [dbo].[question_bank] ([question_id])
GO
ALTER TABLE [dbo].[program_ans] CHECK CONSTRAINT [FK_program_ans_question_bank]
GO
ALTER TABLE [dbo].[question_bank]  WITH CHECK ADD  CONSTRAINT [FK962s4hae81lqpkyacj8n166j3] FOREIGN KEY([difficulty])
REFERENCES [dbo].[difficulty] ([level_id])
GO
ALTER TABLE [dbo].[question_bank] CHECK CONSTRAINT [FK962s4hae81lqpkyacj8n166j3]
GO
ALTER TABLE [dbo].[question_bank]  WITH CHECK ADD  CONSTRAINT [FKhmfthlcf0720i608msrclh5uu] FOREIGN KEY([category])
REFERENCES [dbo].[category] ([category_id])
GO
ALTER TABLE [dbo].[question_bank] CHECK CONSTRAINT [FKhmfthlcf0720i608msrclh5uu]
GO
ALTER TABLE [dbo].[question_bank]  WITH CHECK ADD  CONSTRAINT [FKj7krc5jekd0vg2qp5d0yqq53n] FOREIGN KEY([created_by])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[question_bank] CHECK CONSTRAINT [FKj7krc5jekd0vg2qp5d0yqq53n]
GO
ALTER TABLE [dbo].[question_bank]  WITH CHECK ADD  CONSTRAINT [FKjcsewx7llt2g9enjs38xfy5ym] FOREIGN KEY([updated_by])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[question_bank] CHECK CONSTRAINT [FKjcsewx7llt2g9enjs38xfy5ym]
GO
ALTER TABLE [dbo].[question_bank]  WITH CHECK ADD  CONSTRAINT [FKodecslrob2ld6xdhuu909eq2n] FOREIGN KEY([type])
REFERENCES [dbo].[question_type] ([type_id])
GO
ALTER TABLE [dbo].[question_bank] CHECK CONSTRAINT [FKodecslrob2ld6xdhuu909eq2n]
GO
ALTER TABLE [dbo].[student]  WITH CHECK ADD  CONSTRAINT [FKhahtmjbbf09kwtrgd150xop46] FOREIGN KEY([institute_id])
REFERENCES [dbo].[institute] ([institute_id])
GO
ALTER TABLE [dbo].[student] CHECK CONSTRAINT [FKhahtmjbbf09kwtrgd150xop46]
GO
ALTER TABLE [dbo].[student]  WITH CHECK ADD  CONSTRAINT [FKk0thg920a3xk3v59yjbsatw1l] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[student] CHECK CONSTRAINT [FKk0thg920a3xk3v59yjbsatw1l]
GO
ALTER TABLE [dbo].[enrollment]  WITH CHECK ADD CHECK  (([result]='NA' OR [result]='Fail' OR [result]='Pass'))
GO
ALTER TABLE [dbo].[enrollment]  WITH CHECK ADD CHECK  (([status]='Completed' OR [status]='NotAttempted' OR [status]='Attempted'))
GO
ALTER TABLE [dbo].[exam]  WITH CHECK ADD CHECK  (([exam_status]='Unavailable' OR [exam_status]='Available'))
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD  CONSTRAINT [CK_users] CHECK  (([role]='Super_Admin' OR [role]='Admin' OR [role]='Student'))
GO
ALTER TABLE [dbo].[users] CHECK CONSTRAINT [CK_users]
GO
